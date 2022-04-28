package com.shawn.springboot.web.domain.posts;

import com.shawn.springboot.domain.posts.Posts;
import com.shawn.springboot.domain.posts.PostsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest     //자동으로 H2 데이터베이스를 실행
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach      //단위 테스트가 끝날 때 마다 수행되는 메서드로 지정. / 주로 배포 전 전체 테스트 수행 중 테스트간 데이터 침범을 막기 위해 사용
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트본문";

            //테이블 posts에 id 값이 있으면 update, 없으면 insert 수행
        postsRepository.save(Posts.builder()
                                        .title(title)
                                        .content(content)
                                        .author("shshhan@shawn.com")
                                        .build());

        //when                      //테이블 posts에 있는 모든 데이터를 조회
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
                .title("test_title")
                .content("test_content")
                .author("author")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>> createDate : "+posts.getCreatedDate() +", modifiedDate : " + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }

}
