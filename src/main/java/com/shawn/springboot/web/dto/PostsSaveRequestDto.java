package com.shawn.springboot.web.dto;

import com.shawn.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }

    /**
     * Entity 클래스와 유사한 형태이지만 절대로 Entity 클래스를 Request/Response 클래스로 사용하면 안된다!
     * Entity 클래스는 DB와 맞닿은 핵심 클래스. 이를 기준으로 테이블이 생성되고 스키마가 변경된다.
     * Dto는 View를 위한 클래스이기 때문에 변경이 잦다.
     */
}
