package ezenWeb.model.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass //
@EntityListeners(AuditingEntityListener.class) //AuditingEntityListener 변화가 있는 엔티티 감시 (startApp에 @EnableJpaAuditing 어노테이션 추가)
public class BaseTime {

    // 1.레코드/엔티티 등록날짜
    @CreatedDate // == default now()
    private LocalDateTime cdate;

    // 2.레코드/엔티티 수정날짜
    @LastModifiedDate // 마지막 수정 날짜
    private LocalDateTime udate;

}

/*
    상속 : 여러곳에서 공통적인 멤버들
*/