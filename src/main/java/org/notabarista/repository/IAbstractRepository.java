package org.notabarista.repository;

import java.util.UUID;

import org.notabarista.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IAbstractRepository<T extends AbstractEntity> extends JpaRepository<T, UUID> {

}
