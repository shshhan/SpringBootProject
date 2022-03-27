package com.shawn.springboot.web;

import com.shawn.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@RunWith(OOO.class) : 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행 시킴
//                      여기서는 SpringRunner라는 스프링 실행자를 사용
//                      즉 스프링 부트 테스트와 JUnit 사이의 연결자 역할
//@WebMvcTest : 선언할 경우 @Controller, @ControllerAdvice 등을 사용 가능
//              하지만 @Service, @Component, @Repository등을 사용할 수 없음


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class,    //@WebMvcTest는 JPA 기능이 작동하지 않는다.
            excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)})
public class HelloControllerTest{

    @Autowired
    private MockMvc mvc;    //웹 API 테스트시 사용 / 스프링 MVC 테스트 시작점 / HTTP, GET, POST 등에 대한 API 테스트 가능

    @WithMockUser(roles="USER")
    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) //.perform의 결과를 검증 / HTTP Header의 Status를 검증
                .andExpect(content().string(hello));
    }

    @WithMockUser(roles="USER")
    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 32;

        mvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))   //.param() : 요청파라미터 설정, String만 허용
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))    //jsonPath() : JSON 응답값을 필드별로 검증할 수 있는 메서드. / $를 기준으로 필드명 명시
                .andExpect(jsonPath("$.amount", is(amount)));



    }
}