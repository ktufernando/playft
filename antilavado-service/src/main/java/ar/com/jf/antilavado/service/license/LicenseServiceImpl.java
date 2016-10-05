package ar.com.jf.antilavado.service.license;

import ar.com.jf.antilavado.repository.dto.response.license.LicenseData;
import ar.com.jf.antilavado.repository.interfaces.LicenseRepository;
import ar.com.jf.antilavado.repository.interfaces.UserRepository;
import ar.com.jf.antilavado.repository.model.License;
import ar.com.jf.antilavado.service.exceptions.ServiceException;
import net.nicholaswilliams.java.licensing.LicenseManager;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * LicenseServiceImpl.java
 * <p>
 * PLAYFT.
 * <p>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p>
 * Created by fvaldes on 26/03/2015.
 */
@Service
public class LicenseServiceImpl implements LicenseService {

    private static final Logger LOG = LoggerFactory.getLogger(LicenseServiceImpl.class);

    @Autowired
    private LicenseRepository licenseRepository;

    @Autowired
    private LicenseManager licenseManager;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public LicenseData addLicense(String licenseString) throws Exception {
        LOG.info("The license: " + licenseString);

        net.nicholaswilliams.java.licensing.License l = null;
        try {
            l = licenseManager.getLicense(licenseString);
        } catch (Exception e) {
            LOG.error("The license key is invalid.", e);
            throw new ServiceException("license.key.invalid", "The license key in invalid.");
        }

        /*try {
            licenseManager.validateLicense(l);
        } catch (Exception e) {
            LOG.error("The license can not be validated.", e);
            throw new ServiceException("license.not.validated", "The license can not be validated.");
        }*/

        License license = new License();
        license.setLicense(licenseString);
        license.setCreatedDate(DateTime.now());
        licenseRepository.save(license);

        DateTimeFormatter dtfOut = DateTimeFormat.forPattern("dd/MM/yyyy");

        LicenseData response = new LicenseData(
                l.getHolder(),
                dtfOut.print(l.getIssueDate()),
                l.getSubject(),
                l.getNumberOfLicenses(),
                dtfOut.print(l.getGoodAfterDate()),
                dtfOut.print(l.getGoodBeforeDate())
        );

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public LicenseData getLicenseData() throws Exception {
        License license = licenseRepository.loadLastSave();

        net.nicholaswilliams.java.licensing.License l = licenseManager.getLicense(license.getLicense());

        DateTimeFormatter dtfOut = DateTimeFormat.forPattern("dd/MM/yyyy");

        LicenseData response = new LicenseData(
                l.getHolder(),
                dtfOut.print(l.getIssueDate()),
                l.getSubject(),
                l.getNumberOfLicenses(),
                dtfOut.print(l.getGoodAfterDate()),
                dtfOut.print(l.getGoodBeforeDate())
        );

        response.setActiveUsers(userRepository.activeUsers().intValue());

        return response;
    }
}
