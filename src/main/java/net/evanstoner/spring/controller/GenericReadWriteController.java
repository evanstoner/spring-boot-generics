package net.evanstoner.spring.controller;

import net.evanstoner.spring.entity.BaseEntity;
import net.evanstoner.spring.service.GenericReadWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * A generic controller that allows POST, GET, PUT, and DELETE on a resource. Autowires a service
 * which must implement GenericService. If additional or customized functionality is required, the
 * extending subclass may override these methods or add others.
 *
 * The subclass must add @RestController and @RequestMapping annotations. These are NOT inheritable
 * annotations.
 * @param <E> The domain model that this controller works on.
 * @param <S> The service that this controller uses to make application changes.
 */
public class GenericReadWriteController<E extends BaseEntity, S extends GenericReadWriteService<E>>
        extends GenericReadOnlyController<E, S> {

    @Autowired
    protected S service;

    @RequestMapping(method = RequestMethod.POST)
    public Object post(@RequestBody @Valid E e) {
        return wrapOne(service.create(e));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object put(@RequestBody @Valid E e, @PathVariable Long id) {
        return wrapOne(service.update(e));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
