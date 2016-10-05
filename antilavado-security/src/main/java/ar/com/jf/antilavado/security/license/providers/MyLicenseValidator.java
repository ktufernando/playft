package ar.com.jf.antilavado.security.license.providers;

import net.nicholaswilliams.java.licensing.License;
import net.nicholaswilliams.java.licensing.LicenseValidator;
import net.nicholaswilliams.java.licensing.exception.InvalidLicenseException;
import org.joda.time.DateTime;

/**
 * MyLicenseValidator.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 26/03/2015.
 */
public class MyLicenseValidator implements LicenseValidator {


    @Override
    public void validateLicense(License license) throws InvalidLicenseException {
        try {
            if (new DateTime(license.getGoodBeforeDate()).isBeforeNow()) {
                throw new InvalidLicenseException();
            }
            if (license.getNumberOfLicenses() < 1) {
                throw new InvalidLicenseException();
            }
        }catch (Exception e){
            throw new InvalidLicenseException();
        }
    }
}
