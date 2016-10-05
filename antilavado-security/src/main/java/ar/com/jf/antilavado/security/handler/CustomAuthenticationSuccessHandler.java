package ar.com.jf.antilavado.security.handler;

import ar.com.jf.antilavado.security.user.UserSecurity;
import ar.com.jf.antilavado.security.util.HeaderUtil;
import ar.com.jf.antilavado.repository.dto.response.BasicResponse;
import com.google.gson.Gson;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;

/**
 * CustomAuthenticationSuccessHandler.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 18/09/2015.
 */
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private HeaderUtil headerUtil;

    public CustomAuthenticationSuccessHandler(HeaderUtil headerUtil) {
        super();
        this.headerUtil = headerUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        try {
            String token = headerUtil.createAuthToken(((UserSecurity) authentication.getPrincipal()).getUsername());

            BasicResponse lr = new BasicResponse(token);

            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = response.getWriter();
            out.print(new Gson().toJson(lr));
            out.flush();
            out.close();
        } catch (GeneralSecurityException e) {
            throw new ServletException("Unable to create the auth token", e);
        }
        clearAuthenticationAttributes(request);

    }

}
