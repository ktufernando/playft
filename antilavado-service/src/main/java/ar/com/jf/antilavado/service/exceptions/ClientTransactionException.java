package ar.com.jf.antilavado.service.exceptions;

/**
 * ClientTransactionException.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 14/09/2015.
 */
public class ClientTransactionException extends RuntimeException {

    private static final long serialVersionUID = 7599384208982678521L;

	private String error;


	public ClientTransactionException(String message, Throwable throwable){
		super(message, throwable);
	}

	public ClientTransactionException(String error) {
		super(error);
		this.error = error;
	}


	public String getError() {
		return error;
	}

}
