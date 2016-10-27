package ar.com.fvaldes.licenses.creations;

import net.nicholaswilliams.java.licensing.encryption.PasswordProvider;

/**
 * Created by ktufernando on 25/02/2016.
 */
public class MyLicensePasswordProvider implements PasswordProvider {
    public char[] getPassword() {
        return new char[] {
                'p', 'l', 'a', 'y', 'f', 't'
        };
    }
}
