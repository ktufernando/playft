package ar.com.jf.antilavado.repository.dto.response.files;

/**
 * File.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 13/04/2016.
 */
public class File {

    private String name;
    private String type;

    public File(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public File() {
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
