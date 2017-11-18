package net.evanstoner.spring.test;

import net.evanstoner.spring.service.GenericReadWriteService;

public interface ColorService extends GenericReadWriteService<Color> {

    public Color findByRGB(int red, int green, int blue);

    public Color findByHex(String hex);

}
