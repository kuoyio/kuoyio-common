package cn.kuoyio.common.infrastructure.repository;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@SQLRestriction("is_deleted = false")
@EntityListeners(AuditingEntityListener.class)
public abstract class EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EntityId
    private String id;
    @CreatedDate
    private LocalDateTime createdTime;
    @LastModifiedDate
    private LocalDateTime updatedTime;
    private Boolean isDeleted;
}


