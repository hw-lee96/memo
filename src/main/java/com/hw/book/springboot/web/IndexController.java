package com.hw.book.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping
    public String index() {
//        머스테치 스타터(build.gradle에 추가함) 덕분에 앞의 경로와 뒤의 파일 확장자는 자동으로 지정되어 View Resolver에 전달됨
        return "index";
    }
}
