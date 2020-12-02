package org.notabarista.service.abstr.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.notabarista.converter.GenericConverter;
import org.notabarista.dto.AbstractDTO;
import org.notabarista.entity.AbstractEntity;
import org.notabarista.repository.IAbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public abstract class AbstractService<T extends AbstractEntity, U extends AbstractDTO> {

	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private GenericConverter<T, U> converter;
	
	@Autowired
	protected IAbstractRepository<T> repository;

	@PersistenceContext
	protected EntityManager entityManager;
	
	protected ApplicationContext getApplicationContext() {
		return context;
	}

	protected GenericConverter<T, U> getConverter() {
		return converter;
	}
}
