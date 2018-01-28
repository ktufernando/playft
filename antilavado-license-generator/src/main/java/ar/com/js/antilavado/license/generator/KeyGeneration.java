package ar.com.js.antilavado.license.generator;

import net.nicholaswilliams.java.licensing.encryption.RSAKeyPairGenerator;
import net.nicholaswilliams.java.licensing.encryption.RSAKeyPairGeneratorInterface;
import net.nicholaswilliams.java.licensing.exception.AlgorithmNotSupportedException;
import net.nicholaswilliams.java.licensing.exception.InappropriateKeyException;
import net.nicholaswilliams.java.licensing.exception.InappropriateKeySpecificationException;
import net.nicholaswilliams.java.licensing.exception.RSA2048NotSupportedException;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.security.KeyPair;

public class KeyGeneration {

    public static void main(String[] arguments) {
        /*RSAKeyPairGenerator generator = new RSAKeyPairGenerator();

        KeyPair keyPair;
        try {
            keyPair = generator.generateKeyPair();
        } catch(RSA2048NotSupportedException e) { return; }

        RSAKeyPairGeneratorInterface.GeneratedClassDescriptor rkd = new RSAKeyPairGeneratorInterface.GeneratedClassDescriptor().
                setPackageName("ar.com.js.antilavado.license.generator.license.providers").setClassName("MyLicenseProvider");

        RSAKeyPairGeneratorInterface.GeneratedClassDescriptor ukd = new RSAKeyPairGeneratorInterface.GeneratedClassDescriptor().
                setPackageName("ar.com.js.antilavado.license.generator.license.providers").setClassName("MyFilePublicKeyDataProvider");

        RSAKeyPairGeneratorInterface.GeneratedClassDescriptor pd1 = new RSAKeyPairGeneratorInterface.GeneratedClassDescriptor().
                setPackageName("ar.com.js.antilavado.license.generator.license.providers").setClassName("MyLicensePasswordProvider");

        RSAKeyPairGeneratorInterface.GeneratedClassDescriptor pd2 = new RSAKeyPairGeneratorInterface.GeneratedClassDescriptor().
                setPackageName("ar.com.js.antilavado.license.generator.license.providers").setClassName("MyPublicKeyPasswordProvider");


        try {
            generator.saveKeyPairToProviders(keyPair, rkd, ukd, "playft".toCharArray(), "playft".toCharArray());
            generator.savePasswordToProvider("playft".toCharArray(), pd1);
            generator.savePasswordToProvider("playft".toCharArray(), pd2);
        } catch(AlgorithmNotSupportedException | InappropriateKeyException | InappropriateKeySpecificationException e)
        { return; }

        System.out.println(rkd.getJavaFileContents() + "\n\n" + ukd.getJavaFileContents() + "\n\n" +
                pd1.getJavaFileContents() + "\n\n" + pd2.getJavaFileContents());*/

        RSAKeyPairGenerator generator = new RSAKeyPairGenerator();

        KeyPair keyPair;
        try {
            keyPair = generator.generateKeyPair();
        } catch(RSA2048NotSupportedException e) { return; }

        try {
            generator.saveKeyPairToFiles(keyPair, "playft.private.key", "playft.public.key", "playft".toCharArray(), "playft".toCharArray());
        } catch(IOException | AlgorithmNotSupportedException | InappropriateKeyException | InappropriateKeySpecificationException e)
        { return; }
    }
}
