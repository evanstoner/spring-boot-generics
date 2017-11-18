package net.evanstoner.spring.test;

import org.springframework.data.repository.CrudRepository;

public interface ColorRepository extends CrudRepository<Color, Long> {

    Color findByRedAndGreenAndBlue(int red, int green, int blue);

}
