package com.hw.book.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Java8 에서는 Optional<T> 클래스를 사용해 NPE(Null Point Exception)을 방지할 수 있게 해준다.
    // Null이 나오는 값을 감싸는 Wrapper 클래스이다.
    Optional<User> findByEmail(String email);
}
