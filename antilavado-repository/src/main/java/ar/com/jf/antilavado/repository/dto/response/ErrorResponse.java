package ar.com.jf.antilavado.repository.dto.response;

/**
 * ErrorResponse.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 16/09/2015.
 */
public class ErrorResponse extends BasicResponse<String> {

    public ErrorResponse(String statusCode, String statusMessage) {
        super(statusCode, statusMessage);
    }

    public ErrorResponse(int statusCode, String statusMessage) {
        super(statusCode, statusMessage);
    }

    @Override
    public String getResult() {
        return null;
    }
}
