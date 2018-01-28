package ar.com.js.antilavado.license.generator.license.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * LicenseException.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 01/03/2016.
 */
public class LicenseException extends AuthenticationException {

    private String code;
	private String error;


	public LicenseException(String message, Throwable throwable){
		super(message, throwable);
	}

	public LicenseException(String code, String error) {
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
