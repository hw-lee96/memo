package com.hw.book.springboot.web.dto;

import org.junit.Test;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void lombokMethodTest() {
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        // assertj 라는 검증 라이브러리의 검증 메소드
        // 검증하고 싶은 대상을 메소드 인자로 받으며, 메소드 체이닝이 지원됨
        // Junit 와의 차이점
            // CoreMatchers 와 달리 추가적으로 라이브러리가 필요하지 않음
            // 자동완성이 좀 더 확실하게 됨
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
