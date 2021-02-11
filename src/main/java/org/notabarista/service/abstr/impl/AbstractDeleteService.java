package org.notabarista.service.abstr.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.notabarista.dto.AbstractDTO;
import org.notabarista.entity.AbstractEntity;
import org.notabarista.exception.AbstractNotabaristaException;
import org.notabarista.exception.EntityNotFoundException;
import org.notabarista.service.abstr.IDeleteService;

import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class AbstractDeleteService<T extends AbstractEntity, U extends AbstractDTO>
		extends AbstractWriteService<T, U> implements IDeleteService<T, U> {

	public void preDeleteById(final Integer id) throws AbstractNotabaristaException {
	}

	public void postDeleteById(final Integer id) throws AbstractNotabaristaException {
	}

	@Override
	@Transactional
	public void deleteById(final Integer id) throws AbstractNotabaristaException {
		if (log.isInfoEnabled()) {
			log.info("Delete by id:" + id);
		}

		preDeleteById(id);

		boolean exists = this.repository.existsById(id);

		if (exists) {
			this.repository.deleteById(id);
		} else {
			throw new EntityNotFoundException();
		}

		postDeleteById(id);
	}

	public void preDeleteByIds(final List<Integer> ids) throws AbstractNotabaristaException {
	}

	public void postDeleteByIds(final List<Integer> ids) throws AbstractNotabaristaException {
	}

	@Override
	@Transactional
	public void deleteByIds(final List<Integer> ids) throws AbstractNotabaristaException {
		if (log.isInfoEnabled()) {
			log.info("Delete by ids:" + ids);
		}

		preDeleteByIds(ids);

		for (Integer id : ids) {
			deleteById(id);
		}

		postDeleteByIds(ids);
	}

	@Override
	@Transactional
	public void delete(U dto) throws AbstractNotabaristaException {
		deleteById(dto.getId());
	}

}
