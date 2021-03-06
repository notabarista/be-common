package org.notabarista.service.abstr;

import java.util.List;

import org.notabarista.dto.AbstractDTO;
import org.notabarista.entity.AbstractEntity;
import org.notabarista.exception.AbstractNotabaristaException;

public interface IWriteService<T extends AbstractEntity, U extends AbstractDTO> {

	U insert(U dto) throws Exception;

	List<U> insert(List<U> dtos) throws Exception;

	U update(U dto) throws AbstractNotabaristaException;

	List<U> update(List<U> dtos) throws AbstractNotabaristaException;

}
