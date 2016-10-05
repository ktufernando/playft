package ar.com.jf.antilavado.service.exceptions;

/**
 * FactorHandlerException.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 03/12/2015.
 */
public class FactorHandlerException extends RuntimeException {

	private String error;

	public FactorHandlerException(String error) {
		super(error);
		this.error = error;
	}

	public String getError() {
		return error;
	}

}
