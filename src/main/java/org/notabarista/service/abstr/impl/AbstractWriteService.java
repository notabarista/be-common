package org.notabarista.service.abstr.impl;

import java.util.ArrayList;
import java.util.List;

import org.notabarista.dto.AbstractDTO;
import org.notabarista.entity.AbstractEntity;
import org.notabarista.exception.AbstractNotabarristaException;
import org.notabarista.service.abstr.IWriteService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class AbstractWriteService<T extends AbstractEntity, U extends AbstractDTO>
		extends AbstractReadService<T, U> implements IWriteService<T, U> {

	protected void preInsert(U dto) throws AbstractNotabarristaException {
	}

	protected void postInsert(U dto) throws AbstractNotabarristaException {
	}

	@Override
	@Transactional(rollbackFor = { Exception.class, AbstractNotabarristaException.class })
	public U insert(U dto) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Insert new " + dto.getClass().getSimpleName());
		}
		
		preInsert(dto);
		
		T entity = getConverter().createFrom(dto);
		T savedEntity = this.repository.save(entity);
		U savedDto = getConverter().createFrom(savedEntity);
		
		postInsert(savedDto);
		
		return savedDto;
	}

	protected void preInsert(List<U> entities) throws AbstractNotabarristaException {
	}
	
	public void postInsert(List<U> dtos) {
	}

	@Override
	@Transactional(rollbackFor = { Exception.class,
			AbstractNotabarristaException.class }, isolation = Isolation.DEFAULT)
	public List<U> insert(List<U> dtos) throws Exception {
		if (log.isInfoEnabled()) {
			log.info("Insert new " + dtos.getClass().getSimpleName());
		}
		
		preInsert(dtos);
		List<U> savedDtos = new ArrayList<>();
		for (U dto : savedDtos) {
			U insertedDto = insert(dto);
			savedDtos.add(insertedDto);
		}
		postInsert(savedDtos);
		return savedDtos;
	}

	protected void preUpdate(U dto) throws AbstractNotabarristaException {
	}
	
	public void postUpdate(U dto) throws AbstractNotabarristaException {
	}

	@Override
	@Transactional(rollbackFor = { Exception.class, AbstractNotabarristaException.class })
	public U update(U dto) throws AbstractNotabarristaException {
		if (log.isInfoEnabled()) {
			log.info("Updating " + dto.getClass().getSimpleName());
		}
		preUpdate(dto);
		
		T entity = getConverter().createFrom(dto);
		T savedEntity = this.repository.save(entity);
		U savedDto = getConverter().createFrom(savedEntity);
		
		postUpdate(savedDto);
		return savedDto;
	}

	public void preUpdate(List<U> dtos) throws AbstractNotabarristaException {
	}
	
	public void postUpdate(List<U> dtos) throws AbstractNotabarristaException {
	}

	@Override
	@Transactional(rollbackFor = { Exception.class,
			AbstractNotabarristaException.class }, isolation = Isolation.REPEATABLE_READ)
	public List<U> update(List<U> dtos) throws AbstractNotabarristaException {
		if (log.isInfoEnabled()) {
			log.info("Update " + dtos.getClass().getSimpleName());
		}
		
		preUpdate(dtos);
		List<U> savedDtos = new ArrayList<>();
		for (U dto : savedDtos) {
			U updatedDto = update(dto);
			savedDtos.add(updatedDto);
		}
		postUpdate(savedDtos);
		
		return savedDtos;
	}

}
