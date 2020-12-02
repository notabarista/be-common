package org.notabarista.dto;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class AbstractDTO {
	
	@ApiModelProperty(example = "1234", notes = "The id is generated on DB side, and is PK")
	protected Integer id;

}
