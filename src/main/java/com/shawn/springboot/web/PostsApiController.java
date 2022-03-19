package com.shawn.springboot.web;

import com.shawn.springboot.service.posts.PostsService;
import com.shawn.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor    //final로 선언된 모든 필드를 인자값으로 하는 생성자를 생성
@RestController
public class PostsApiController {
    private final PostsService postsService;
    //Bean을 주입 받는 방식들(@Autowired, setter, 생성자) 중 으뜸은 생성자로 주입 받기.
    //--> final로 필드를 선언하고 @RequiredArgsConstructor로 생성자 생성
    //직접 생성자를 만드는 대신 롬복을 사용하는 이유 : 클래스의 의존성 관계가 변경될 때마다 코드를 계속 수정하는 번거로움을 해결하기 위해


    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }
}
