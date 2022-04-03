package com.hw.book.springboot.web;

import com.hw.book.springboot.config.auth.LoginUser;
import com.hw.book.springboot.config.auth.dto.SessionUser;
import com.hw.book.springboot.service.posts.PostsService;
import com.hw.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
//    private final HttpSession httpSession;

//    @GetMapping
//    public String index() {
////        머스테치 스타터(build.gradle에 추가함) 덕분에 앞의 경로와 뒤의 파일 확장자는 자동으로 지정되어 View Resolver에 전달됨
//        return "index";
//    }

//    @GetMapping("/")
//    public String index(Model model) {
//        model.addAttribute("posts", postsService.findAllDesc());
//
//        // CustumOAuth2UserService 에서 로그인 성공 시 세션에 SessionUser 를 저장
//        // 즉, 로그인 성공 시 httpSession.getAttribute("user")에서 값을 가져올 수 있다.
//        SessionUser user = (SessionUser)httpSession.getAttribute("user");
//
//        // 세션에 저장된 값이 있을 때만 model 에 userName 으로 등록
//        // 세션에 저장된 값이 없으면 model 에는 아무런 값이 없는 상태이니 로그인 버튼이 보이게 된다.
//        if ( user != null ) {
//            model.addAttribute("userName", user.getName());
//        }
//
//        return "index";
//    }

    @GetMapping("/")
    // 반복되는 부분을 @LoginUser 로 개선
    // 기존에 (User) httpSession.getAttribute("user") 로 가져오던 세션 정보 값이 개선됨
    // 어느 컨트롤러에서든 @LoginUser 를 사용하면 세션 정보를 가져올 수 있음
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        if ( user != null ) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }


    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
