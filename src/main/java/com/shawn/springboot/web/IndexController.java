package com.shawn.springboot.web;

import com.shawn.springboot.config.auth.LoginUser;
import com.shawn.springboot.config.auth.dto.SessionUser;
import com.shawn.springboot.service.posts.PostsService;
import com.shawn.springboot.web.dto.PostsResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;
    /**
     * mustach starter 덕분에 컨트롤러에서 문자열을 반환할 때 앞의 경로와 뒤의 파일 확장자는 자동으로 지정된다.
     * 앞의 경로 : src/main/resources/templates
     * 뒤의 확장자 : .mustache
     * 최종 경로를 View Resolver가 처리
     * @return
     */
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){   //model : 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
        model.addAttribute("posts", postsService.findAllDesc());

        if(user != null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }


}
