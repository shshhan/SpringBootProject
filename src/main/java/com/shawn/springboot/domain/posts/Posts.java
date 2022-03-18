package com.shawn.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity     //테이블과 링크될 클래스임을 나타냄 / 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름을 매칭
public class Posts {

    @Id //해당 테이블의 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK의 생성 규칙. Spring Boot 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야 auto_increment
    private Long id;

    @Column(length = 500, nullable = false)     //테이블의 컬럼을 나타내며, 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 컬럼. 변경이 필요한 옵션이 있을경우 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder        //해당 클래스의 빌더 패턴 클래스를 생성. 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    /**
     * Getter와 Setter를 무작정 생성하지 말자!
     * 무작성 생성하면 해당 클래스의 인스턴스 값들이 언제 어디서 변해야하는지 코드상으로 명확하게 구분이 어려워 추후 기능 변경 시 복잡하다.
     * => Entity 클래스에서는 Setter를 만들지 않느다. 값의 변경이 꼭 필요하다면 그 목적과 의도가 분명한 메서드를 생성하여 Setter의 역할을 대신 한다.
     * ex) public void setStatus(boolean status){ this.status = status; } (X)
     *     public void cancleOrder() { this.status = false; } (O)
     */

    /**
     * Setter가 없다면 필드에 값은 어떻게 채울 수 있나?
     * 생성자를 통해 최종값을 채운 후 DB에 삽입.
     * 값의 변경이 필요하다면 위에서 말한 대로 해당 이벤트의 목적과 의도가 분명한 public 메서드를 호출하여 변경
     *
     */
}
