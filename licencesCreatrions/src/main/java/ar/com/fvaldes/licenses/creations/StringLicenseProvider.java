package ar.com.fvaldes.licenses.creations;

import net.nicholaswilliams.java.licensing.DeserializingLicenseProvider;
import org.apache.commons.codec.binary.Base64;

/**
 * Created by ktufernando on 25/02/2016.
 */
public class StringLicenseProvider extends DeserializingLicenseProvider {

    @Override
    protected byte[] getLicenseData(Object o) {
        return Base64.decodeBase64(((String) o).getBytes());
    }
}
