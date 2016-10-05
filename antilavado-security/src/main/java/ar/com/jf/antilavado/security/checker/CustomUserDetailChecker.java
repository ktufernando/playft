package ar.com.jf.antilavado.security.checker;

import ar.com.jf.antilavado.repository.dto.response.license.LicenseData;
import ar.com.jf.antilavado.security.license.exception.LicenseException;
import ar.com.jf.antilavado.service.license.LicenseService;
import net.nicholaswilliams.java.licensing.exception.InvalidLicenseException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

/**
 * CustomUserDetailChecker.java
 * <p>
 * PLAYFT.
 * <p>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p>
 * Created by fvaldes on 01/03/2016.
 */
public class CustomUserDetailChecker implements UserDetailsChecker{

    private static final Logger LOGGER = LoggerFactory
            .getLogger(CustomUserDetailChecker.class);
    private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
    private LicenseService licenseService;

    public CustomUserDetailChecker(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @Override
    public void check(UserDetails user) {

        try{
            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
            LicenseData licenseData = licenseService.getLicenseData();
            if (formatter.parseDateTime(licenseData.getLicenseExpiration()).isBeforeNow()) {
                LOGGER.debug("License is expired");
                throw new LicenseException("license.expiration", "License is expired");
            }
        }catch (Exception e){
            throw new LicenseException("license.not.get", "License not get");
        }

        if(!user.isAccountNonLocked()) {
            LOGGER.debug("User account is locked");
            throw new LockedException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.locked", "User account is locked"), user);
        } else if(!user.isEnabled()) {
            LOGGER.debug("User account is disabled");
            throw new DisabledException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.disabled", "User is disabled"), user);
        } else if(!user.isAccountNonExpired()) {
            LOGGER.debug("User account is expired");
            throw new AccountExpiredException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.expired", "User account has expired"), user);
        }

    }
}
