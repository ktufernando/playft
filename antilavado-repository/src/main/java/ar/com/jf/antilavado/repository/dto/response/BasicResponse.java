package ar.com.jf.antilavado.repository.dto.response;

/**
 * BasicResponse.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 27/8/15.
 */
public class BasicResponse<T> implements Response<T> {

    private String statusCode;
    private String statusMessage;
    private T result;

    public BasicResponse(String statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public BasicResponse(int statusCode, String statusMessage) {
        this(String.valueOf(statusCode), statusMessage);
    }

    public BasicResponse(String statusCode, String statusMessage, T result) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.result = result;
    }

    public BasicResponse(int statusCode, String statusMessage, T result) {
        this(String.valueOf(statusCode), statusMessage, result);
    }

    public BasicResponse(T result) {
        this.statusCode = "200";
        this.statusMessage = "OK";
        this.result = result;
    }

    public String getStatusCode() {
        return statusCode;
    }


    public String getStatusMessage() {
        return statusMessage;
    }

    @Override
    public T getResult() {
        return result;
    }

}
