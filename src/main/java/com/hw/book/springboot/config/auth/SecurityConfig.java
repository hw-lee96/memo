package com.hw.book.springboot.config.auth;

import com.hw.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
// Spring Security 설정들을 활성화 시킨다.
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // h2-console 화면을 사용하기 위한 disable 설정 1
                // CSRF 란 Cross Site Request  Forgery 의 약자로 사이트 간 요청 위조를 뜻한다.
                // 정상적인 사용자가 의도치 않은 우조 요청을 보내는 것을 의미하며, 스프링 시큐리티에서 default로 설정된다.
                // 설정되면, GET 요청을 제외한 POST, PUT, DELETE 요청으로부터 보호하게 된다.
                // 하지만 rest api 를 이용한 서버라면 session 기반 인증과는 다르게 stateless (서버에 세션 상태가 없음)이기 때문에
                // 서버에 인증정보룰 보관하지 않는다. 대신 권한이 필요한 요청을 해야할 때는 인증정보(OAuth2m JWT 등)을 포함시켜야 한다.
                // 따라서 rest api의 세팅으로는 불필요하기 때문에 disable 처리를 하는 것이다.
                .csrf().disable()
                // h2-console 화면을 사용하기 위한 disable 설정 2
                // headers 의 X-Frame-Options 를 DENY 로 설정하는 내용이다.
                // 웹 사이트에 프레임을 허용하는 것은 클릭 재킹과 같은 공격에 취약하기 때문에 설정한다.
                // 설정 시 웹 브라우저는 프레임 내부에서 페이지가 렌더링 되는 것을 막는다.
                // 프레임이란 <frame> 또는 <iframe>, <object> 를 말한다.
                // 클릭재킹이란 웹 사용자가 자신이 클릭하고 있다고 인지하는 것과 다른 어떠한 것을 클릭하게 속이는 것이다.
                .headers().frameOptions().disable()
                .and()
                    // URL 별 권한 관리를 설정하는 옵션의 시작점
                    // authorizeRequests 가 선언되어야만 antMatchers를 사용할 수 있다.
                    .authorizeRequests()
                        // 권한 관리 대상을 지정하는 옵션
                        // URL, HTTP 메소드별로 관리가 가능하다.
                        // "/" 등 지정된 URL 들은 permitAll 옵션을 통해 전체 열람 권한이 설정됨
                        .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                        // "/api/v1/**" 는 USER 권한을 가진 사람만 접근이 가능하도록 설정됨
                        .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                        // 그 외 나머지 URL에 관한 설정
                        // authenticated 추가로 인증된 사용자들에게만 허용하도록 함 (로그인한 사용자)
                        .anyRequest().authenticated()
                .and()
                    // 로그아웃 관련 설정의 진입점
                    .logout()
                        // 로그아웃 성공 시 지정한 URL로 이동시킴
                        .logoutSuccessUrl("/")
                .and()
                    // OAuth 2 로그인 기능에 대한 여러 설정의 진입점
                    .oauth2Login()
                        // OAuth 2 로그인 성공 이후 사용자 정보를 가져올 때의 설정을 담당
                        .userInfoEndpoint()
                            // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
                            // 리소스 서버(즉, 소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있음.
                            .userService(customOAuth2UserService);
    }
}
