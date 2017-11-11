package net.evanstoner.spring.service;

import net.evanstoner.spring.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;

/**
 * A generic service implementation that uses corresponding CrudRepository methods to support findAll,
 * findOne, create, update, and delete methods.
 * @param <E> The primary domain model that this service works on.
 * @param <R> The repository to use to persist requests.
 */
public abstract class GenericReadWriteServiceImpl<E extends BaseEntity, R extends CrudRepository<E, Long>>
        extends GenericReadOnlyServiceImpl<E, R>
        implements GenericReadWriteService<E> {

    private static final Logger LOG = LoggerFactory.getLogger(GenericReadWriteServiceImpl.class);

    @Override
    public E create(E e) {
        LOG.debug("using generic create implementation: " + e);
        return save(e);
    }

    @Override
    public E update(E e) {
        LOG.debug("using generic update implementation: " + e);
        return save(e);
    }

    /**
     * A convenience method around this service's repository save() method, which will create-or-update.
     * @param e
     * @return
     */
    protected final E save(E e) {
        return repository.save(e);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("using generic delete implementation: " + id);
        repository.delete(id);
    }
}
