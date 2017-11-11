package net.evanstoner.spring.service;

import net.evanstoner.spring.entity.BaseEntity;

/**
 * A generic service interface which supports findAll, findOne, update, and delete methods. Extending
 * interfaces may add additional methods, e.g. findByFirstName, as appropriate.
 * @param <E> The primary domain model that this service works on.
 */
public interface GenericReadWriteService<E extends BaseEntity> extends GenericReadOnlyService<E> {

    /**
     * Persist a new record.
     * @param e
     * @return
     */
    E create(E e);

    /**
     * Update an existing record.
     * @param e
     * @return
     */
    E update(E e);

    void delete(Long id);

}
