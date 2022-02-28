package com.hw.book.springboot.web;

import com.hw.book.springboot.service.posts.PostsService;
import com.hw.book.springboot.web.dto.PostsResponseDto;
import com.hw.book.springboot.web.dto.PostsSaveRequestDto;
import com.hw.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// Controller 와 Serivce 에서 @Autowired가 없는 이유
// 스프링에선 Bean 을 주입받는 방식이 1.@Autowired  2.setter  3.생성자 이렇게 3가지가 있다.
// 이 중 가장 권장하는 방식은 생성자로 주입받는 방식이며, @Autowired는 권장하는 방식이 아니다.
// @RequiredArgsConstructor 는 이러한 생성자로 주입하는 방식으로 주입을 처리해준다. (final이 선언된 모든 필드를 인자값으로 하는 생성자를 생성)
// 이처럼 롬복 어노테이션을 이용한 방식을 사용하면 해당 클래스의 의존성 관계가 변경될 때 별도의 수정을 하지 않아도 된다는 장점이 있다.
@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete (@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
