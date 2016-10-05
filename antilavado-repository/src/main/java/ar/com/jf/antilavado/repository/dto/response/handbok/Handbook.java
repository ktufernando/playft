package ar.com.jf.antilavado.repository.dto.response.handbok;

/**
 * Handbook.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */
public class Handbook {

    private String name;
    private String type;

    public Handbook(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Handbook() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
