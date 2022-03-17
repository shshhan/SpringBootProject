package com.shawn.springboot.web;

import com.shawn.springboot.web.dto.HelloResponseDto;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트(){
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
        //assertThat : assertj라는 테스트 검증 라이브러리의 검증 메서드
        //           : 검증하고 싶은 대상을 메서드 인자로 받음
        //           : 메서드 체이닝 지원되어 .isEqualTo()와 같이 메서드 이서서 사용 가능
        //isEqualTo  : assertj의 동등 비교 메서드
        //           : assertThat에 있는 값과 isEqualTo의 값을 비교해서 같을 때만 성공

        /**
         * assertj VS JUnit
         * assertj의 장점
         * 1. CoreMatchers와 달리 추가적으로 라이브러리가 필요하지 않다.
         *      JUnit의 assertThat은 is()와 같이 CoreMatchers 라이브러리가 필요하다.
         * 2. 자동완성 지원이 더욱 확실하다.
         *      IDE에서는 CoreMatchers와 같은 Matcher 라이브러리의 자동완성 지원이 약하다.
         */
    }
}
