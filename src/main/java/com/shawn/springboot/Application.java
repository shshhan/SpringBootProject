package com.shawn.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  //스프링 부트의 자동설정, Bean 읽기와 생성을 모두 자동으로 설정
                        //이 어노테이션이 있는 위치부터 설정을 읽어가기 때문에 항상 프로젝트 최상단에 위치해야한다.
public class Application {  //메인 클래스
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); //내장 WAS를 실행
    }
}
