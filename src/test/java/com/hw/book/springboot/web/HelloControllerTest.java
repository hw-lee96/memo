package com.hw.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.is;

// 테스트 진행 시 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴
@RunWith(SpringRunner.class)

// 선언할 경우 @Controller, @ControllerAdvice 등을 사용할 수 있음
// @Service, @Component, @Repository 등은 사용 못함
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    // 스프링이 관리하는 Bean을 주입 받음
    @Autowired
    // 웹 API를 테스트할 때 사용하며, HTTP GET, POST 등에 대한 API 테스트를 할 수 있음
    private MockMvc mvc;

    @Test
    public void returnToHello() throws Exception {  // 해당 라인 좌측의 화살표(라인 숫자 바로 옆)를 클릭하여 해당 메소드를 실행시킬 수 있다.
        String hello = "hello";

        // MockMvc를 통해 /hello 주소로 HTTP GET 요청을 함
        // 체이닝이 지원되어 아래와 같이 여러 검증 기능을 이어서 선언 가능
        mvc.perform(get("/hello"))
                // mvc.perform의 결과를 검증하며, 현재 로직에서는 HTTP Header의 Status를 검증
                // 우리가 흔히 알고 있는 200, 404, 500 등의 상태를 검증
                // 여기선 OK 즉, 200인지 아닌지를 검증
                .andExpect(status().isOk())
                // 응답 본문의 내용을 검증하며, Controller 에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증하게 됨
                .andExpect(content().string(hello));
    }

    @Test
    public void returnToHelloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                    get("/hello/dto")
                            // .param() : API 테스트에 사용될 요청 파라미터를 설정. 값은 String 만 허용
                            .param("name", name)
                            .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                // jsonPath() : JSON 응답값을 필드별로 검증할 수 있는 메소드. $를 기준으로 필드명을 명시
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}