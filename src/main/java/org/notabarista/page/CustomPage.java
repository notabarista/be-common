package org.notabarista.page;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class CustomPage<T extends Object> {

	@Getter
	@Setter
	private List<T> data;

	@Getter
	@Setter
	private Integer pageNumber;

	@Getter
	@Setter
	private Integer pageSize;

	@Getter
	@Setter
	private Long total;

	public Integer getTotalPages() {
		Double totalPages = Double.valueOf(total) / (Double.valueOf(pageSize));
		return Double.valueOf(Math.ceil(totalPages)).intValue();
	}

}
