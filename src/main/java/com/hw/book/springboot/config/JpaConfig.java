package com.hw.book.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration

// JPA Auditing 활성화
// 원래는 Application.java 에 있었는데 분리
@EnableJpaAuditing  
public class JpaConfig {
}
