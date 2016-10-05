package ar.com.jf.antilavado.rest.handler.exception;

import ar.com.jf.antilavado.repository.dto.response.BasicResponse;
import ar.com.jf.antilavado.repository.dto.response.ErrorResponse;
import ar.com.jf.antilavado.security.license.exception.LicenseException;
import ar.com.jf.antilavado.service.exceptions.FactorHandlerException;
import ar.com.jf.antilavado.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;

/**
 * ExceptionsHandler.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 30/10/2015.
 */
@ControllerAdvice
public class ExceptionsHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public
    @ResponseBody
    BasicResponse handleException(Exception e) {
        return new ErrorResponse("500",
                messageSource.getMessage("error.system", null,
                        Locale.getDefault()));
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public
    @ResponseBody
    BasicResponse handleException(ServiceException e) {
        return new ErrorResponse("500",
                messageSource.getMessage(e.getCode(), null,
                        Locale.getDefault()));
    }

    @ExceptionHandler(FactorHandlerException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public
    @ResponseBody
    BasicResponse handleException(FactorHandlerException e) {
        return new ErrorResponse("500", e.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public
    @ResponseBody
    BasicResponse usernameNotFounfException(UsernameNotFoundException e) {
        return new ErrorResponse("401",
                messageSource.getMessage("error.user.not.found", null,
                        Locale.getDefault()));
    }

    @ExceptionHandler(LicenseException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public
    @ResponseBody
    BasicResponse licenseExcception(LicenseException e) {
        return new ErrorResponse("401",
                messageSource.getMessage(e.getCode(), null,
                        Locale.getDefault()));
    }

    @ExceptionHandler(DisabledException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public
    @ResponseBody
    BasicResponse disabledException(DisabledException e) {
        return new ErrorResponse("401",
                messageSource.getMessage("error.user.not.enabled", null,
                        Locale.getDefault()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
     @ResponseStatus(value = HttpStatus.NOT_FOUND)
     public
     @ResponseBody
     BasicResponse requestMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        return new ErrorResponse("404",
                messageSource.getMessage("error.method.not.supported", null,
                        Locale.getDefault()));
    }

}
