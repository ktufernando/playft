package ar.com.jf.antilavado.security.license.providers;

import net.nicholaswilliams.java.licensing.LicenseProvider;
import net.nicholaswilliams.java.licensing.SignedLicense;

/**
 * MyLicenseProvider.java
 * <p>
 * PLAYFT.
 * <p>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p>
 * Created by fvaldes on 26/03/2015.
 */
public class MyLicenseProvider implements LicenseProvider {


    public SignedLicense getLicense(Object o) {
        return new StringLicenseProvider().getLicense(o);
    }

}
