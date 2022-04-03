package com.hw.book.springboot.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    // JPA로 DB에 저장할 때 Enum 값을 어떤 형태로 저장할지 설정
    // 기본적으로는 int로 된 숫자가 저장됨
    // 숫자로 저장되면 DB로 확인할 때 그 값이 무슨 코드를 의미하는지 알 수 없기 때문에 문자열(EnumType.STRING)로 저장될 수 있도록 선언
    // 권한코드에는 항상 'ROLE_[STRING]'이 앞에 있어야한다.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
