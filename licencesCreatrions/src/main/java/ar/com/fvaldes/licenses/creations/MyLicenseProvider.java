package ar.com.fvaldes.licenses.creations;

import net.nicholaswilliams.java.licensing.LicenseProvider;
import net.nicholaswilliams.java.licensing.SignedLicense;

/**
 * Created by ktufernando on 25/02/2016.
 */
public class MyLicenseProvider implements LicenseProvider {


    public SignedLicense getLicense(Object o) {
        return new StringLicenseProvider().getLicense(o);
    }

}
