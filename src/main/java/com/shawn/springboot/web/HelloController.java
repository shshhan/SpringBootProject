package com.shawn.springboot.web;

import com.shawn.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
    //1. 컨트롤러를 JSON을 반환하는 컨트롤러로 변경
    //2. 모든 메서드마다 @ResponseBody를 선언한 것과 같음
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name      //"name"으로 넘긴 파라미터를 메서드 파라미터 name에 저장
                                    , @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
}
