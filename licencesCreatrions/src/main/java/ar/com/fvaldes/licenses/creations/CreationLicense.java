package ar.com.fvaldes.licenses.creations;

import net.nicholaswilliams.java.licensing.License;
import net.nicholaswilliams.java.licensing.licensor.LicenseCreator;
import net.nicholaswilliams.java.licensing.licensor.LicenseCreatorProperties;
import org.joda.time.DateTime;

/**
 * Created by ktufernando on 25/02/2016.
 */
public class CreationLicense {

    public static void main(String[] arguments) {
        LicenseCreatorProperties.setPrivateKeyDataProvider(new SampleFilePrivateKeyDataProvider());
        LicenseCreatorProperties.setPrivateKeyPasswordProvider(new MyPrivateKeyPasswordProvider());

        DateTime date = DateTime.now().plusYears(1);
        License license = new License.Builder().
                withHolder("Test").
                withGoodBeforeDate(date.getMillis()).
                withIssueDate(DateTime.now().getMillis()).
                withSubject("Comercial").
                withNumberOfLicenses(5).
                withGoodAfterDate(date.getMillis()).
                build();

        byte[] licenseData = LicenseCreator.getInstance().signAndSerializeLicense(license, "playft".toCharArray());
        System.out.println(new String(org.apache.commons.codec.binary.Base64.encodeBase64(licenseData)));

    }
}
