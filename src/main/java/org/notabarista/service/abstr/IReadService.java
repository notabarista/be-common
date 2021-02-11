package org.notabarista.service.abstr;

import java.util.List;

import org.notabarista.dto.AbstractDTO;
import org.notabarista.entity.AbstractEntity;
import org.notabarista.exception.AbstractNotabaristaException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface IReadService<T extends AbstractEntity, U extends AbstractDTO> extends IBaseService<T, U> {

	Page<U> findAll(Pageable pageable) throws AbstractNotabaristaException;

	U findById(Integer id) throws AbstractNotabaristaException;

	List<U> findAll() throws AbstractNotabaristaException;

	List<U> findAllSorted(Sort sort) throws AbstractNotabaristaException;

	List<U> findByDTO(U dto) throws AbstractNotabaristaException;

	Page<U> findByDTO(U dto, Pageable pageable) throws AbstractNotabaristaException;

}
