package com.shawn.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * DB Layer 접근자
 * 인터페이스를 생성 후 JpaRepository<Entity 클래스, PK 타입>을 implements하면 기본 CRUD 메서드가 자동으로 생성된다.
 * 반드시 Entity 클래스와 같은 디렉토리에 위치해야한다.
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {

    /**
     * SpringDataJPA에서 제공하지 않는 메서드는 아래와 같이 @Query와 함께 쿼리문으로 작성할 수 있다.
     */
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

    /**
     * 규모가 있는 프로젝트에서의 데이터 조회는 FK의 Join, 복잡한 조건 등으로 인해 Entity 클래스만으로 처리하기 어려워 조회용 프레임워크를 추가로 사용한다.
     * 대표적으로 querydsl, jooq, myBatis가 있다.
     * 프레임워크로 조회하고, 등록/수정/삭제는 SpringDataJpa를 사용한다.
     */

}
