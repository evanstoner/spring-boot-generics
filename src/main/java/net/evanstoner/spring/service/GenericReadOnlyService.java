package net.evanstoner.spring.service;

import net.evanstoner.spring.entity.BaseEntity;

public interface GenericReadOnlyService<E extends BaseEntity> {

    Iterable<E> findAll();

    E findOne(Long id);

}
