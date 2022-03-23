package com.shawn.springboot.service.posts;

import com.shawn.springboot.domain.posts.Posts;
import com.shawn.springboot.domain.posts.PostsRepository;
import com.shawn.springboot.web.dto.PostsListResponseDto;
import com.shawn.springboot.web.dto.PostsResponseDto;
import com.shawn.springboot.web.dto.PostsSaveRequestDto;
import com.shawn.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                                        .orElseThrow( () -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id) );
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
        /**
         * update 기능에서는 JPA의 영속성 컨텍스트(엔티티를 영구 저장하는 환경) 덕분에 쿼리를 직접 날리지 않아도 된다.
         * JPA의 엔티티 매니저가 활성화된 상태(Spring Data JPA를 싸용하면 기본 옵션)에서는 트랜잭션 안에서 DB 데이터를 가져오면 해당 데이터는 영속성 컨텍스트가 유지되는 상태
         * 이 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경된 데이터를 반영한다.
         * Entity 객체의 값 변경 만으로 update. 더티 체킹이라고 표현.
         *
         */
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                                        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
        //map(PostsListResponseDto::new) == map(posts -> new PostsListResponseDto(posts))
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts);  //delete()는 엔티티를 파라미터로 삭제할 수 있고, deleteBuId()를 사용하면 id로 삭제할 수도 있다.  /여기서는 존재하는 Posts인지 확인을 위해 엔티티 조회 후 그대로 삭제

    }
}
