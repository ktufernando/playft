package ar.com.js.antilavado.license.generator;

import ar.com.js.antilavado.license.generator.license.providers.MyLicensePasswordProvider;
import ar.com.js.antilavado.license.generator.license.providers.MyPrivateKeyDataProvider;
import net.nicholaswilliams.java.licensing.License;
import net.nicholaswilliams.java.licensing.encryption.RSAKeyPairGenerator;
import net.nicholaswilliams.java.licensing.exception.AlgorithmNotSupportedException;
import net.nicholaswilliams.java.licensing.exception.InappropriateKeyException;
import net.nicholaswilliams.java.licensing.exception.InappropriateKeySpecificationException;
import net.nicholaswilliams.java.licensing.exception.RSA2048NotSupportedException;
import net.nicholaswilliams.java.licensing.licensor.LicenseCreatorProperties;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.util.UUID;

public class LicenseCreator {

    public static void main(String[] arguments) {
        LicenseCreatorProperties.setPrivateKeyDataProvider(new MyPrivateKeyDataProvider());
        LicenseCreatorProperties.setPrivateKeyPasswordProvider(new MyLicensePasswordProvider());
        net.nicholaswilliams.java.licensing.licensor.LicenseCreator creator = net.nicholaswilliams.java.licensing.licensor.LicenseCreator.getInstance();

        Long now = DateTime.now().getMillis();
        Long expirationDate = DateTime.now().plusYears(30).getMillis();
        String key = UUID.randomUUID().toString();

        License license = new License.Builder().
                withProductKey(key).
                withHolder("Organización Test").
                withSubject("Coorporativo").
                withGoodBeforeDate(expirationDate).
                withGoodAfterDate(now).
                withIssueDate(now).
                withNumberOfLicenses(10).
                build();

        byte[] licenseData = creator.signAndSerializeLicense(license, "playft".toCharArray());

        byte[] encoded = Base64.encodeBase64(licenseData);
        System.out.println(new String(encoded));


    }
}
