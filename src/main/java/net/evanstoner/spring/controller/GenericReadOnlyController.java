package net.evanstoner.spring.controller;

import net.evanstoner.spring.entity.BaseEntity;
import net.evanstoner.spring.service.GenericReadOnlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class GenericReadOnlyController<E extends BaseEntity, S extends GenericReadOnlyService<E>>
        extends GenericBaseController<E> {

    @Autowired
    protected S service;

    @RequestMapping(method = RequestMethod.GET)
    public Object get() {
        return wrapMany(service.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Object get(@PathVariable Long id) {
        return wrapOne(service.findOne(id));
    }

}
