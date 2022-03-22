package com.shawn.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    /**
     * mustach starter 덕분에 컨트롤러에서 문자열을 반환할 때 앞의 경로와 뒤의 파일 확장자는 자동으로 지정된다.
     * 앞의 경로 : src/main/resources/templates
     * 뒤의 확장자 : .mustache
     * 최종 경로를 View Resolver가 처리
     * @return
     */
    @GetMapping("/")
    public String index(){
        return "index";
    }

}
