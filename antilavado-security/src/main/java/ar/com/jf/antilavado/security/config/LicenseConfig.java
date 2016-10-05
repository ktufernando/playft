package ar.com.jf.antilavado.security.config;

import ar.com.jf.antilavado.security.license.providers.*;
import net.nicholaswilliams.java.licensing.LicenseManager;
import net.nicholaswilliams.java.licensing.LicenseManagerProperties;
import net.nicholaswilliams.java.licensing.encryption.RSAKeyPairGenerator;
import net.nicholaswilliams.java.licensing.exception.RSA2048NotSupportedException;
import net.nicholaswilliams.java.licensing.licensor.LicenseCreator;
import net.nicholaswilliams.java.licensing.licensor.LicenseCreatorProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyPair;

/**
 * LicenseConfig.java
 * <p>
 * PLAYFT.
 * <p>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p>
 * Created by fvaldes on 26/03/2015.
 */
@Configuration
public class LicenseConfig {

    @Bean
    public LicenseManager licenseManager() {
        LicenseManagerProperties.setPublicKeyDataProvider(new MyFilePublicKeyDataProvider());
        LicenseManagerProperties.setPublicKeyPasswordProvider(new MyPublicKeyPasswordProvider());
        LicenseManagerProperties.setLicenseProvider(new MyLicenseProvider());

        // Optional; set only if you are using a different password to encrypt licenses than your public key
        LicenseManagerProperties.setLicensePasswordProvider(new MyLicensePasswordProvider());

        // Optional; set only if you wish to validate licenses
        LicenseManagerProperties.setLicenseValidator(new MyLicenseValidator());

        // Optional; defaults to 0, which translates to a 10-second (minimum) cache time
        //LicenseManagerProperties.setCacheTimeInMinutes();

        return LicenseManager.getInstance();
    }

}
