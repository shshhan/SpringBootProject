package com.shawn.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DB Layer 접근자
 * 인터페이스를 생성 후 JpaRepository<Entity 클래스, PK 타입>을 implements하면 기본 CRUD 메서드가 자동으로 생성된다.
 * 반드시 Entity 클래스와 같은 디렉토리에 위치해야한다.
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
