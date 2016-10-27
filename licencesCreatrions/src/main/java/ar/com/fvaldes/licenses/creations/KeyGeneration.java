package ar.com.fvaldes.licenses.creations;

import net.nicholaswilliams.java.licensing.encryption.RSAKeyPairGenerator;
import net.nicholaswilliams.java.licensing.exception.RSA2048NotSupportedException;

import java.security.KeyPair;

/**
 * Created by ktufernando on 25/02/2016.
 */
public class KeyGeneration {
    public static void main(String[] arguments) {
        RSAKeyPairGenerator generator = new RSAKeyPairGenerator();

        KeyPair keyPair;
        try {
            keyPair = generator.generateKeyPair();
        } catch (RSA2048NotSupportedException e) {
            return;
        }

        try {
            generator.saveKeyPairToFiles(keyPair, "playft.private.key", "playft.public.key", "pl4yft".toCharArray(), "playft".toCharArray());
        } catch (Exception e) {
            return;
        }
    }
}
