package net.evanstoner.spring.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

// @MappedSuperclass - subclasses inherit field mappings, but this class has no table
@MappedSuperclass
// @JsonIdentityInfo - when referencing this model by ID, use the "id" field
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public abstract class BaseEntity {

    @Id
    // we use SEQUENCE here to ensure every record has a unique id across the entire database;
    // this is especially important because we use entity inheritance and therefore subclasses
    // cannot use the same id's
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    public BaseEntity() {}

    public BaseEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    // a subclass may append additional information to this representation in square brackets; e.g.
    //   return super.toString() + "[" + this.getField() + "]";
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "#" + id;
    }

}
