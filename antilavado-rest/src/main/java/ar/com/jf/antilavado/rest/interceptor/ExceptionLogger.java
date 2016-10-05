package ar.com.jf.antilavado.rest.interceptor;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * ExceptionLogger.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 30/10/2015.
 */
@Aspect
@Component
public class ExceptionLogger {

	private static final Logger LOGGER = LoggerFactory
			.getLogger("ExceptionLogger");


	@Pointcut("within(ar.com.jf.antilavado.*..*)")
	public void loggingPointcut() {
	}

	@AfterThrowing(pointcut = "loggingPointcut()", throwing = "e")
	public void exceptionLog(Exception e) {
		LOGGER.error(e.getMessage(), e);
	}
}
