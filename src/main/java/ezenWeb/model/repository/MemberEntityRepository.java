package ezenWeb.model.repository;

import ezenWeb.model.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface MemberEntityRepository extends JpaRepository<MemberEntity, Integer> {

    //1. 특정 필드의 조건으로 레코드/엔티티 검색
    MemberEntity findByMemail(String memail);

    //2. 특정 필드의 조건으로 존재여부 검색
    boolean existsByMemail(String memail);

    //3. 직접 native SQL 지원
    //@Query (value = "SQL작성", nativeQuery = true)
        //sql 매개변수 대입
            //:매개변수명
    @Query (value = "select * from member where memail = :memail", nativeQuery = true)
    MemberEntity findByMeamilSQL(String memail);
    // ================== 로그인 ==================
    //1.
    MemberEntity findByMemailAndMpassword(String memail, String mpassword);
    //2.
    boolean existsByMemailAndMpassword(String memail, String mpassword);
    //3.
    @Query(value = "select * from member" +
            " where memail = :memail and mpassword = :mpassword", nativeQuery = true)
    MemberEntity findByLoginSQL(String memail, String mpassword);

    // ================== 내가쓴글 ==================
    //1. 양방향일때는 회원 엔티티를 통해 게시물 호출 가능
    //2. 단방향일때는 회원 엔티티를 이용한 게시물 호출 시 조인 query
    @Query(value = "select * from member m inner join board b" +
            " on m.mno = b.mno_fk where m.mno = :mno", nativeQuery = true)
    List<Map<Object,Object>> findbyMyBoardSQL(int mno);
    /*
        List : 여러개 레코드
        Map<> : 하나의 레코드 안에서 필드와 값
            Map<필드명, 필드값>
        ex)
            mno mid     mname



     */

}

/*
    - 리포지토리 만드는 방법
    1. @Repository
    2. extends JpaRepository<조작할 엔티티, pk의필드타입>

    - 리포지토리 이용한 CRUD 메소드
    1. save(엔티티) : 해당 엔티티개체를 테이블에 삽입 INSERT

    추상메소드
        1. 특정 필드의 조건으로 레코드/엔티티 검색
            반환타입 findby필드명(조건매개변수);
        2. 특정 필드의 조건으로 존재여부 검색
            boolean existBy필드명(조건매개변수);

    - 반환타입



 */