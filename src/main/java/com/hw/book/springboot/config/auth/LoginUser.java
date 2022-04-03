package com.hw.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 해당 어노테이션이 생성될 수 있는 위치를 지정
// PARAMETER 로 지정되었을 때, 메소드의 파라미터로 선언된 객체에서만 사용가능
@Target(ElementType.PARAMETER)

@Retention(RetentionPolicy.RUNTIME)
// 어노테이션 기반으로 개선하기 위한 클래스
// @interface : 해당 파일을 어노테이션 클래스로 지정. LoginUser 라는 이름의 어노테이션이 생성 됨
public @interface LoginUser {
}
