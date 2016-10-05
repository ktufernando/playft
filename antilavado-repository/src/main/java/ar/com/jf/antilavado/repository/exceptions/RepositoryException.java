package ar.com.jf.antilavado.repository.exceptions;

/**
 * RepositoryException.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 16/09/2015.
 */
public class RepositoryException extends RuntimeException {

	private static final long serialVersionUID = 3760571314324972189L;

	private String code;
	private String error;


	public RepositoryException(String message, Throwable throwable){
		super(message, throwable);
	}

	public RepositoryException(String code, String error) {
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
