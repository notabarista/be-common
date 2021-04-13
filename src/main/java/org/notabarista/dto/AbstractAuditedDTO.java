package org.notabarista.dto;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class AbstractAuditedDTO extends AbstractDTO {

	protected LocalDate createdAt;
	protected LocalDate modifiedAt;
	protected String createdBy;
	protected String modifiedBy;
	protected Integer version;
	
}
