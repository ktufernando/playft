package ar.com.jf.antilavado.service.exceptions;

/**
 * ServiceException.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 14/09/2015.
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 7599384208982678521L;

    private String code;
	private String error;


	public ServiceException(String message, Throwable throwable){
		super(message, throwable);
	}

	public ServiceException(String code, String error) {
		super(error);
		this.code = code;
		this.error = error;
	}

	public String getCode() {
		return code;
	}

	public String getError() {
		return error;
	}

}
