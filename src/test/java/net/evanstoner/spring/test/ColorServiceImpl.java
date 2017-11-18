package net.evanstoner.spring.test;

import net.evanstoner.spring.service.GenericReadWriteServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ColorServiceImpl
        extends GenericReadWriteServiceImpl<Color, ColorRepository>
        implements ColorService {

    @Override
    public Color findByRGB(int red, int green, int blue) {
        return this.repository.findByRedAndGreenAndBlue(red, green, blue);
    }

    @Override
    public Color findByHex(String hex) {
        String validHexRegex = "[0-9a-fA-F]{6}";
        if (!hex.matches(validHexRegex)) {
            throw new IllegalArgumentException("hex must match " + validHexRegex);
        }
        int red = Integer.parseInt(hex.substring(0, 2), 16);
        int green = Integer.parseInt(hex.substring(2, 4), 16);
        int blue = Integer.parseInt(hex.substring(4, 6), 16);
        return this.repository.findByRedAndGreenAndBlue(red, green, blue);
    }
}
