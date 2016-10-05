package ar.com.jf.antilavado.security.license.providers;

import net.nicholaswilliams.java.licensing.encryption.PasswordProvider;

/**
 * MyLicensePasswordProvider.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 26/03/2015.
 */
public class MyLicensePasswordProvider implements PasswordProvider {
    public char[] getPassword() {
        return new char[] {
                'p', 'l', 'a', 'y', 'f', 't'
        };
    }
}
