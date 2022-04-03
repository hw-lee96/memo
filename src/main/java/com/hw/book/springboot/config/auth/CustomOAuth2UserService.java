package com.hw.book.springboot.config.auth;

import com.hw.book.springboot.config.auth.dto.OAuthAttributes;
import com.hw.book.springboot.config.auth.dto.SessionUser;
import com.hw.book.springboot.domain.user.User;
import com.hw.book.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 현재 로그인 진행 중인 서비스를 구분하는 코드
        // 지금은 구글만 사용하고 있지만, 이후 네이버 로그인 연동 시 구글인지, 네이버인지 구분하기 위해 사용
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // OAuth2 로그인 진행 시 키가 되는 필드값을 이야기한다. Primary Key와 같은 의미다.
        // 구글은 기본적으로 코드를 지원하지만, 네이버나 카카오 등은 기본 지원하지 않는다. 구글의 기본 코드는 "sub" 이다.
        // 이후 네이버 로그인과 구글 로그인을 동시 지원할 때 사용한다.
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        // OAuth2UserService 를 통해 가져온 OAuth2User 의 attribute를 담을 클래스
        // 이후 네이버 등 다른 소셜 로그인도 이 클래스를 사용한다.
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);

        // 세션에 사용자 정보를 저장하기 위한 Dto 클래스
        // 원래 있던 User 클래스를 쓰지 않고 새로 생성해서 쓰는 이유는 직렬화 때문이다.
        // 세션에 User 클래스를 저장하려고 할 때 원래 생성해놓은 User 클래스를 사용하면 직렬화를 구현하지 않았다는 에러가 발생한다.
        // 여기서 User 클래스에 직렬화 코드를 넣게 된다면 @OneToMany, @ManyToMany 등 자식 엔티티를 가지고 있는 경우 직렬화 대상에 자식들 까지 포함되게 된다.
        // 이런 경우 성능 이슈, 부수 효과가 발생할 확률이 높다.
        // 그렇기 때문에 직렬화 기능을 가진 세션 Dto를 하나 추가로 만드는 것이 추후 유지보수에 많은 도움이 된다.
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    // 구글 사용자 정보가 업데이트 되었을 때를 대비해 update 기능도 구현
    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
