package ar.com.jf.antilavado.repository.dto.response;

/**
 * Response.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 16/09/2015.
 */
public interface Response<T> {

    String getStatusCode();

    String getStatusMessage();

    T getResult();
}
