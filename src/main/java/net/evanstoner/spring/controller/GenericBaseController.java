package net.evanstoner.spring.controller;

import com.fasterxml.jackson.annotation.JsonRootName;
import net.evanstoner.spring.entity.BaseEntity;
import org.springframework.core.GenericTypeResolver;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GenericBaseController<E extends BaseEntity> {

    @SuppressWarnings("unchecked")
    protected Class<E> entityClass() {
        return (Class<E>) GenericTypeResolver.resolveTypeArguments(getClass(), GenericBaseController.class)[0];
    }

    protected String pluralize(String s) {
        return s + "s";
    }

    protected String jsonRootOf(Class c) {
        JsonRootName rootName = (JsonRootName)c.getAnnotation(JsonRootName.class);
        if (rootName != null) {
            return rootName.value();
        } else {
            return "object";
        }
    }

    protected String jsonRootOf(Object o) {
        return jsonRootOf(o.getClass());
    }

    protected Map<String, E> wrapOne(E e) {
        Map<String, E> map = new HashMap<>();
        String jsonRoot = jsonRootOf(entityClass());
        map.put(jsonRoot, e);
        return map;
    }

    protected Map<String, Iterable<E>> wrapMany(Iterable<E> ds) {
        Map<String, Iterable<E>> map = new HashMap<>();
        String jsonRoot = pluralize(jsonRootOf(entityClass()));
        if (ds != null && ds.iterator().hasNext()) {
            map.put(jsonRoot, ds);
        } else {
            map.put(jsonRoot, Collections.emptyList());
        }
        return map;
    }

}
