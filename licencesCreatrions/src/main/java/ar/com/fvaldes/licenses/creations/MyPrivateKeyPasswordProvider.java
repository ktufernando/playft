package ar.com.fvaldes.licenses.creations;

import net.nicholaswilliams.java.licensing.encryption.PasswordProvider;

/**
 * Created by ktufernando on 25/02/2016.
 */
public class MyPrivateKeyPasswordProvider implements PasswordProvider {
    public char[] getPassword() {
        return new char[] {
                'p', 'l', '4', 'y', 'f', 't'
        };
    }
}
