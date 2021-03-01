package org.notabarista.dto;

import java.util.UUID;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
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
public class AbstractDTO {
	
	@ApiModelProperty(example = "df5d7a7a-782c-11eb-9439-0242ac130002", notes = "The id is generated at ORM Hibernate level, and is PK")
//	protected Integer id;
	protected UUID id;

}
