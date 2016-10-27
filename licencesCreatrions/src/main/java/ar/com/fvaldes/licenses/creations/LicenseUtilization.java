package ar.com.fvaldes.licenses.creations;

import net.nicholaswilliams.java.licensing.License;
import net.nicholaswilliams.java.licensing.LicenseManager;
import net.nicholaswilliams.java.licensing.LicenseManagerProperties;
import net.nicholaswilliams.java.licensing.exception.ExpiredLicenseException;
import net.nicholaswilliams.java.licensing.exception.InvalidLicenseException;
import net.nicholaswilliams.java.licensing.licensor.LicenseCreator;
import net.nicholaswilliams.java.licensing.licensor.LicenseCreatorProperties;
import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by ktufernando on 25/02/2016.
 */
public class LicenseUtilization {

    public static void main(String[] arguments) {
        LicenseManagerProperties.setPublicKeyDataProvider(new SampleFilePublicKeyDataProvider());
        LicenseManagerProperties.setPublicKeyPasswordProvider(new MyPublicKeyPasswordProvider());
        LicenseManagerProperties.setLicenseProvider(new MyLicenseProvider());

        // Optional; set only if you are using a different password to encrypt licenses than your public key
        LicenseManagerProperties.setLicensePasswordProvider(new MyLicensePasswordProvider());

        // Optional; set only if you wish to validate licenses
        //LicenseManagerProperties.setLicenseValidator(new MyLicenseValidator());

        // Optional; defaults to 0, which translates to a 10-second (minimum) cache time
        //LicenseManagerProperties.setCacheTimeInMinutes(5);

        LicenseManager manager = LicenseManager.getInstance();

        License license = manager.getLicense("rO0ABXNyADFuZXQubmljaG9sYXN3aWxsaWFtcy5qYXZhLmxpY2Vuc2luZy5TaWduZWRMaWNlbnNlioT/n36yaoQCAAJbAA5saWNlbnNlQ29udGVudHQAAltCWwAQc2lnbmF0dXJlQ29udGVudHEAfgABeHB1cgACW0Ks8xf4BghU4AIAAHhwAAAAUOxqu42lP3qOzBQhyvpJ82dsid4MGHRf0suAwAUtmGNJcMU+cGeV5t3VsWsAzCp+MKaz7Ozv5lCBWSTtsoDzga/JT1165bakdTuJYrc7WtiqdXEAfgADAAABAG2LwJNmLYgPoeYrmv1ygXXW+lUhdwg+UW8HXLn4an6rUFQ6oAspPKXxgY6UpTfskBXe08YXAZDggpikSMGuigMusTv/zmwGrJpIE75GruMuTtqFu/TRc74TEhYodRCiJ5o9o6Q625rTzghWIdbpAtEXcSE+3PRfRLNShBWEs/PDA1I50K0kVSV27GPtqaNbTZYAo0E4gB7tomjxJ24e19AGYyflNGBkK0XR4Uz9BqLYm2aYByK5qWXOFQB1SmS7WwPpmox5n2oUlNmnW+PXNsaxgO1t5cl45kbBlKL/R6GnlYYn9/pq4STDiOG5JyLFO9GZ9gwfVqMDZe8+FdXznwI=");
        try {
            manager.validateLicense(license);
        //} catch(ExpiredLicenseException | InvalidLicenseException e) {
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Numero de licencias > " + license.getNumberOfLicenses());
        System.out.println("Empresa > " + license.getHolder());
        System.out.println("Vencimiento > " + new DateTime(license.getGoodBeforeDate()).toString("dd/MM/yyyy"));



    }
}
