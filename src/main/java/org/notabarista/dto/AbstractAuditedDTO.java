package org.notabarista.dto;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class AbstractAuditedDTO extends AbstractDTO {

	private Date createdAt;
	private Date modifiedAt;
	private String createdBy;
	private String modifiedBy;
	private Integer version;
	
}
