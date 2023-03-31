package com.ewok.springbootproject.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate    // Entity가 생성되어 저장될 때 시간이 자동 저장
    private LocalDateTime createdDate;

    @LastModifiedDate   // 조회한 Entity의 값을 변경할 때 시간이 자동 저장
    private LocalDateTime modifiedDate;
}


/*

@EntityListeners(AuditingEntityListener.class)
JPA에서 엔티티에서 일어나는 CRUD(Create, Read, Update, Delete) 작업 이전이나 이후에 자동으로 특정 동작을 수행하기 위해 사용하는 어노테이션
AuditingEntityListener는 Spring Data JPA에서 제공하는 엔티티 리스너 중 하나로,
엔티티에서 @CreatedDate, @CreatedBy, @LastModifiedDate, @LastModifiedBy와 같은 필드를 사용하여 생성일, 수정일, 생성자, 수정자 정보를 자동으로 업데이트할 수 있도록 지원
@EntityListeners(AuditingEntityListener.class) 어노테이션을 @Entity 어노테이션이 붙은 엔티티 클래스에 추가하면,
해당 엔티티에서 CRUD 작업이 일어날 때마다 AuditingEntityListener에서 정의한 동작이 수행

@MappedSuperclass
JPA에서 엔티티 클래스들 간에 공통으로 사용되는 매핑 정보를 추상화하여 상속을 통해 코드 중복을 최소화할 수 있도록 지원하는 어노테이션
@MappedSuperclass가 선언된 클래스는 테이블과 매핑되지 않지만,
해당 클래스를 상속받은 하위 클래스에서 @Entity 어노테이션을 사용하여 테이블과 매핑할 수 있음
BaseTimeEntity 클래스를 상속할 경우 필드들인 createdDate, modifiedDate도 상속받은 엔티티 클래스의 칼럼으로 인식
 */