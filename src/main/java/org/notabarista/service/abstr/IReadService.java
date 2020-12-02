package org.notabarista.service.abstr;

import java.util.List;

import org.notabarista.dto.AbstractDTO;
import org.notabarista.entity.AbstractEntity;
import org.notabarista.exception.AbstractNotabarristaException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface IReadService<T extends AbstractEntity, U extends AbstractDTO> {

	Page<U> findAll(Pageable pageable) throws AbstractNotabarristaException;

	U findById(Integer id) throws AbstractNotabarristaException;

	List<U> findAll() throws AbstractNotabarristaException;

	List<U> findAllSorted(Sort sort) throws AbstractNotabarristaException;
	
	List<U> findByDTO(U dto) throws AbstractNotabarristaException;
	
	Page<U> findByDTO(U dto, Pageable pageable) throws AbstractNotabarristaException;
	
}
