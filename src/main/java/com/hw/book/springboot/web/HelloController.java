package com.hw.book.springboot.web;


import com.hw.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// @RestController 어노테이션
// JSON을 반환하는 컨트롤러로 만들어준다.
// 예전에 @ResponseBody를 각 메소드마다 선언했던 것이 한 번에 사용할 수 있게 됨
@RestController
public class HelloController {

    // HTTP Method인 Get 요청을 받을 수 있는 API를 만들어준다.
    // 예전에는 @RequestMapping( method = RequestMethod.GET) 으로 사용되었음
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    // @RequestParam : 외부에서 API 로 넘긴 파라미터를 가져오는 어노테이션
    public HelloResponseDto helloDto(@RequestParam("name") String name
                                    , @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}