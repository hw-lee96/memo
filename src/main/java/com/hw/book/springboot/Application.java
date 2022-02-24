package com.hw.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// JPA Auditing 활성화
@EnableJpaAuditing
// @SpringBootApplication 어노테이션이 있는 위치부터 설정을 읽어가게 됨
// 해당 클래스 main 메소드의 SpringApplication.run 으로 인해 내장 WAS를 실행하게된다.
// 스프링 부트의 자동 설정, 스프링 Bean 읽기 및 생성을 자동화
@SpringBootApplication                                                          
public class Application {                                                      // 앞으로 만들 프로젝트의 메인 클래스
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}