package org.notabarista.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditedEntity extends AbstractEntity {

	@CreatedDate
	@Column(name = "created_at", updatable = false)
	protected LocalDateTime createdAt;

	@LastModifiedDate
	@Column(name = "modified_at")
	protected LocalDateTime modifiedAt;

	@Column(name = "created_by", updatable = false)
	@CreatedBy
	protected String createdBy;

	@Column(name = "modified_by")
	@LastModifiedBy
	protected String modifiedBy;

	@Version
	@Column
	protected Integer version;

}
