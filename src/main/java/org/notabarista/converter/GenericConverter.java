package org.notabarista.converter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.notabarista.dto.AbstractDTO;
import org.notabarista.entity.AbstractEntity;

public interface GenericConverter<T extends AbstractEntity, U extends AbstractDTO> {

	T createFrom(U dto);

	U createFrom(T entity);

	U updateEntity(T entity, U dto);

	default List<U> createFromEntities(final Collection<T> entities) {
		return entities.stream().map(this::createFrom).collect(Collectors.toList());
	}

	default List<T> createFromDto(final Collection<U> dtos) {
		return dtos.stream().map(this::createFrom).collect(Collectors.toList());
	}

}
