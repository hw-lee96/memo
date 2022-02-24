package com.hw.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// ibatis나 myBatis 등에서 Dao라고 불리는 DB Layer 접근자
// JPA 에서는 Repository 로 부르며, 인터페이스로 생성한다.
// JpaRepository<Entity 클래스, PK 타입>를 상속하면 기본적인 CRUD 메소드가 자동으로 생성된다.
// @Repository 어노테이션을 추가할 필요는 없으나, 상속받을 Entity 클래스와는 함께 위치해야 한다.
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
