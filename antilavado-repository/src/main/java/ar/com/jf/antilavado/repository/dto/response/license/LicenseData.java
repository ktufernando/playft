package ar.com.jf.antilavado.repository.dto.response.license;

/**
 * BasicResponse.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 25/02/16.
 */
public class LicenseData {

    private String organization;
    private String datePurchased;
    private String licenseType;
    private int userLimit;
    private String maintenancePeriod;
    private String licenseExpiration;
    private int activeUsers;

    public LicenseData() {
    }

    public LicenseData(String organization, String datePurchased, String licenseType, int userLimit, String maintenancePeriod, String licenseExpiration) {
        this.organization = organization;
        this.datePurchased = datePurchased;
        this.licenseType = licenseType;
        this.userLimit = userLimit;
        this.maintenancePeriod = maintenancePeriod;
        this.licenseExpiration = licenseExpiration;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(String datePurchased) {
        this.datePurchased = datePurchased;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public int getUserLimit() {
        return userLimit;
    }

    public void setUserLimit(int userLimit) {
        this.userLimit = userLimit;
    }

    public String getMaintenancePeriod() {
        return maintenancePeriod;
    }

    public void setMaintenancePeriod(String maintenancePeriod) {
        this.maintenancePeriod = maintenancePeriod;
    }

    public String getLicenseExpiration() {
        return licenseExpiration;
    }

    public void setLicenseExpiration(String licenseExpiration) {
        this.licenseExpiration = licenseExpiration;
    }

    public int getActiveUsers() {
        return activeUsers;
    }

    public void setActiveUsers(int activeUsers) {
        this.activeUsers = activeUsers;
    }
}
