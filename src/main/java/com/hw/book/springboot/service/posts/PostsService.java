package com.hw.book.springboot.service.posts;

import com.hw.book.springboot.domain.posts.Posts;
import com.hw.book.springboot.domain.posts.PostsRepository;
import com.hw.book.springboot.web.dto.PostsResponseDto;
import com.hw.book.springboot.web.dto.PostsSaveRequestDto;
import com.hw.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    // 여기서 update 쿼리를 날리는 부분이 없는데, 이는 JPA의 영속성 컨텍스트 때문이다.
    // 영속성 컨텍스트란, 엔티티를 영구 저장하는 환경을 말한다. 일종의 논리적 개념으로 보면 되며,
    // JPA 의 핵심 내용은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐로 갈린다.
    // Spring Data Jpa를 쓴다면 JPA 엔티티 매니저는 기본 옵션으로 활성화 되어있다.
    // JPA 엔티티 매니저는 트랜잭션 안에서 데이터베이스의 데이터를 가져오면 영속성 컨텍스트를 유지시킨다.
    // 그리고 이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분만을 반영한다.
    // 즉, Entity 객체의 값만 변경하면 별도로 Update 쿼리를 날릴 필요가 없다는 것이며, 이를 더티 체킹이라고 한다.
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow( () -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow( () -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new PostsResponseDto(entity);
    }
}
