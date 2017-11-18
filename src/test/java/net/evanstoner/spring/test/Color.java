package net.evanstoner.spring.test;

import com.fasterxml.jackson.annotation.JsonRootName;
import net.evanstoner.spring.entity.BaseEntity;

import javax.persistence.Entity;

@Entity
@JsonRootName("color")
public class Color extends BaseEntity {

    private int red;
    private int green;
    private int blue;

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }
}
