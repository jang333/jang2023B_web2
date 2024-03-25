package ezenWeb.model.repository;

import ezenWeb.model.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberEntityRepository extends JpaRepository<MemberEntity, Integer> {

}

/*
    - 리포지토리 만드는 방법
    1. @Repository
    2. extends JpaRepository<조작할 엔티티, pk의필드타입>

    - 리포지토리 이용한 CRUD 메소드
    1. save(엔티티) : 해당 엔티티개체를 테이블에 삽입 INSERT



 */