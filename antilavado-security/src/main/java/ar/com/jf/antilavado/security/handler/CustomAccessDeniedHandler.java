package ar.com.jf.antilavado.security.handler;

import ar.com.jf.antilavado.repository.dto.response.BasicResponse;
import ar.com.jf.antilavado.repository.dto.response.ErrorResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * CustomAccessDeniedHandler.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 18/09/2015.
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private MessageSource messageSource;

    public CustomAccessDeniedHandler(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        PrintWriter out = response.getWriter();

        BasicResponse bresponse = new ErrorResponse(String.valueOf(HttpServletResponse.SC_FORBIDDEN),
                messageSource.getMessage("error.security.not.authenticated", null,
                        Locale.getDefault()));

        out.print(new Gson().toJson(bresponse));
        out.flush();
        out.close();

    }
}
