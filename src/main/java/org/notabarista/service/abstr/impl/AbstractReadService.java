package org.notabarista.service.abstr.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.notabarista.dto.AbstractDTO;
import org.notabarista.entity.AbstractEntity;
import org.notabarista.exception.AbstractNotabaristaException;
import org.notabarista.service.abstr.IReadService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class AbstractReadService<T extends AbstractEntity, U extends AbstractDTO> extends AbstractService<T, U>
		implements IReadService<T, U> {

	private static final String ID = "id";
	
	@Override
	public Page<U> findAll(Pageable pageable) throws AbstractNotabaristaException {
		if (log.isInfoEnabled()) {
			log.info("Finding all");
		}
		if (log.isDebugEnabled()) {
			log.debug("Pageable:" + pageable);
		}
		
		if (pageable.getSort().isUnsorted()) {
			pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Direction.ASC, ID));
		}
		Page<T> entities = repository.findAll(pageable);
		
		Page<U> dtoPage = entities.map(getConverter()::createFrom);
		return dtoPage;
	}
	
	@Override
	public List<U> findAllSorted(Sort sort) throws AbstractNotabaristaException {
		if (log.isInfoEnabled()) {
			log.info("Finding all sorted");
		}
		if (log.isDebugEnabled()) {
			log.debug("Finding all sorted:" + sort);
		}
		
		if (sort.isUnsorted()) {
			sort = Sort.by(Direction.ASC, ID);
		}
		List<T> entities = repository.findAll(sort);
		
		List<U> dtos = entities.stream().map(getConverter()::createFrom).collect(Collectors.toList());
		
		return dtos;
	}
	
	@Override
	public List<U> findAll() throws AbstractNotabaristaException {
		if (log.isInfoEnabled()) {
			log.info("Finding all");
		}
		
		List<T> entities = repository.findAll(Sort.by(Sort.Direction.ASC, ID));
		
		List<U> dtos = entities.stream().map(getConverter()::createFrom).collect(Collectors.toList());
		
		return dtos;
	}

	@Override
	public U findById(Integer id) throws AbstractNotabaristaException {
		if (log.isInfoEnabled()) {
			log.info("Find by id:" + id);
		}
		
		Optional<T> opt = repository.findById(id);
		if (opt.isPresent()) {
			T entity = opt.get();
			List<T> retList = new ArrayList<>();
			retList.add(entity);
			
			U dto = getConverter().createFrom(entity);
			
			return dto;
		} else {
			throw new EntityNotFoundException();
		}
	}

	@Override
	public Page<U> findByDTO(U dto, Pageable pageable) throws AbstractNotabaristaException {
		if (log.isInfoEnabled()) {
			log.info("Finding by dto: " + dto.getClass().getSimpleName());
		}
		if (log.isDebugEnabled()) {
			log.debug("Finding by dto:" + dto);
		}
		
		if (pageable.getSort().isUnsorted()) {
			pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Direction.ASC, ID));
		}
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase().withIgnoreNullValues()
				.withIgnorePaths(ID);
		
		T entity = getConverter().createFrom(dto);
		
		Example<T> example = Example.of(entity, exampleMatcher);
		Page<T> entities = repository.findAll(example, pageable);
		
		Page<U> dtoPage = entities.map(getConverter()::createFrom);
		
		return dtoPage;
	}

	@Override
	public List<U> findByDTO(U dto) throws AbstractNotabaristaException {
		if (log.isInfoEnabled()) {
			log.info("Finding by dto: " + dto.getClass().getSimpleName());
		}
		if (log.isDebugEnabled()) {
			log.debug("Finding by dto:" + dto);
		}
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase().withIgnoreNullValues()
				.withIgnorePaths(ID);
		
		T entity = getConverter().createFrom(dto);
		
		Example<T> example = Example.of(entity, exampleMatcher);
		List<T> entities = repository.findAll(example);
		
		List<U> dtos = entities.stream().map(getConverter()::createFrom).collect(Collectors.toList());
		
		return dtos;
	}

}
