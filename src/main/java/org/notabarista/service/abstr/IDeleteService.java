package org.notabarista.service.abstr;

import java.util.List;

import org.notabarista.dto.AbstractDTO;
import org.notabarista.entity.AbstractEntity;
import org.notabarista.exception.AbstractNotabaristaException;

public interface IDeleteService<T extends AbstractEntity, U extends AbstractDTO> extends IWriteService<T, U> {

	void deleteById(Integer id) throws AbstractNotabaristaException;

	void deleteByIds(List<Integer> ids) throws AbstractNotabaristaException;

	void delete(U dto) throws AbstractNotabaristaException;

}
