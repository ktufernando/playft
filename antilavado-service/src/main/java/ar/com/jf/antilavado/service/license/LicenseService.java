package ar.com.jf.antilavado.service.license;

import ar.com.jf.antilavado.repository.dto.response.license.LicenseData;

/**
 * LicenseService.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 25/02/2016.
 */
public interface LicenseService {

    LicenseData addLicense(String licenseString) throws Exception;
    LicenseData getLicenseData() throws Exception;

}
