package com.hw.book.springboot.web.dto;

import com.hw.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
// Entity 와 거의 유사한 형태임에도 Dto 클래스를 추가 생성한다.
// 이유는 Entity 를 Request/Response 로 사용하지 않기 위함인데, 이유는 아래와 같다.
// Entity 는 데이터베이스와 맞닿은 핵심 클래스로 Entity 를 기준으로 테이블이 생성되고, 스키마가 변경되게 된다.
// 화면 변경을 아주 사소한데, 이를 위해 테이블과 연결된 Entity 를 변경하는 것은 너무 큰 변경이기 때문이다.
// 또, Controller 에서 결과값으로 여러 테이블을 조인해서 줘야 할 경우가 빈번한데, 이러한 내용은 Entity 클래스만으로는 표현하기가 어렵기 때문도 있다.
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
