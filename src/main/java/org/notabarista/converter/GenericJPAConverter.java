package org.notabarista.converter;

import org.notabarista.dto.AbstractDTO;
import org.notabarista.entity.AbstractEntity;

public interface GenericJPAConverter<T extends AbstractEntity, S extends AbstractDTO> extends GenericConverter<T, S> {
}
