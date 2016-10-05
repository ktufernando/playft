package ar.com.jf.antilavado.security.user;

import ar.com.jf.antilavado.repository.model.User;
import ar.com.jf.antilavado.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * CustomUserDetailsService.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 07/04/2015.
 */
@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        User user = userService.findOneByLogin(login);
        if (user == null) {
            LOGGER.error("Username " + login + " not found");
            throw new UsernameNotFoundException("Username " + login + " not found");
        }
        LOGGER.info("User for UserDetails: {}", user);
        return new UserSecurity(user);
    }

}
