package ar.com.js.antilavado.license.generator.license.providers;

import net.nicholaswilliams.java.licensing.encryption.PrivateKeyDataProvider;
import net.nicholaswilliams.java.licensing.exception.KeyNotFoundException;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStreamReader;

public class MyPrivateKeyDataProvider implements PrivateKeyDataProvider {


    public byte[] getEncryptedPrivateKeyData() throws KeyNotFoundException {
        try {
            return IOUtils.toByteArray(
                    new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("playft.private.key"), "ISO-8859-1"),
                    "ISO-8859-1"
            );
        }
        catch(IOException e) {
            throw new KeyNotFoundException("The private key file was not found.", e);
        }
    }


}
