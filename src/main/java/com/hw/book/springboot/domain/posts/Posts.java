package com.hw.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// setter를 무작정 생성하지 않는다. 이렇게 되면 해당 클래스의 인스턴스 값들이 언제 어디서 변해야하는지 코드상으로 명확하게 구분할 수 없다. 
// 해당 필드의 값을 수정해야 되는 경우, 직접 setter를 이용하는 것이 아니라 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가해야한다.
// ex ) public void cancelOrder() { this.status = false; } 직접 set하는 것이 아닌, 용도를 알 수 있는 함수를 이용하도록 작성한 예
@Getter
@NoArgsConstructor
// 테이블과 링크될 클래스임을 나타냄
// 기본값으로 클래스의 카멜케이스를 언더스코어 네이밍(_)으로 변환하여 테이블 이름과 매칭한다.
// SalesManager .java -> sales_manager table
@Entity
public class Posts {
    // 해당 테이블의 PK 필드
    @Id
    // PK의 생성규칙
    // 스프링 부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment가 된다.
    // 웬만하면 Entity의 PK는 Long 타입의 Auto_increment를 추천
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 테이블의 칼럼을 나타냄. 굳이 선언하지 않아도 해당 클래스의 필드는 모두 칼럼이 됨.
    // 그럼에도 사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있기 때문.
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // 생성자 대신 @Builder를 사용하는 이유
    // 지금 채워야 할 필드가 무엇인지 명확히 지정할 수 있다.
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
