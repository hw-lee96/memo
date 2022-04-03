package com.hw.book.springboot.config.auth.dto;

import com.hw.book.springboot.domain.user.User;
import lombok.Getter;

@Getter
// SessionUser 에서는 인증된 사용자 정보만을 필요로 함
public class SessionUser {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
 