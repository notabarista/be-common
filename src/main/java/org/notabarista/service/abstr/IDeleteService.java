package org.notabarista.service.abstr;

import java.util.List;
import java.util.UUID;

import org.notabarista.dto.AbstractDTO;
import org.notabarista.entity.AbstractEntity;
import org.notabarista.exception.AbstractNotabaristaException;

public interface IDeleteService<T extends AbstractEntity, U extends AbstractDTO> extends IWriteService<T, U> {

	void deleteById(UUID id) throws AbstractNotabaristaException;

	void deleteByIds(List<UUID> ids) throws AbstractNotabaristaException;

	void delete(U dto) throws AbstractNotabaristaException;

}
