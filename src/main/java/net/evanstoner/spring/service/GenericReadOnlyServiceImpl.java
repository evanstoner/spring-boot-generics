package net.evanstoner.spring.service;

import net.evanstoner.spring.entity.BaseEntity;
import net.evanstoner.spring.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public class GenericReadOnlyServiceImpl<E extends BaseEntity, R extends CrudRepository<E, Long>> implements GenericReadOnlyService<E> {

    private static final Logger LOG = LoggerFactory.getLogger(GenericReadOnlyServiceImpl.class);

    @Autowired
    protected R repository;

    @Override
    public Iterable<E> findAll() {
        LOG.debug("using generic findAll implementation");
        return repository.findAll();
    }

    @Override
    public E findOne(Long id) {
        LOG.debug("using generic findOne implementation: " + id);
        E e = repository.findOne(id);
        if (e == null) {
            throw new EntityNotFoundException();
        }
        return e;
    }

}
