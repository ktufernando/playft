package ar.com.jf.antilavado.repository.model.view;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ClientLevelView.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 17/02/16.
 */
@Entity
@Table(name = "VW_CLIENTS_LEVEL")
public class ClientLevelView implements Serializable {

    @Id
    @Column(name = "CLIENT_ID")
    private Long id;

    @Column(name = "NAMES")
    private String names;

    @Column(name = "LAST_NAMES", length = 100)
    private String lastNames;

    @Column(name = "BUSINESS_NAME", length = 100)
    private String businessName;

    @Column(name = "DOCUMENT_NUMBER", length = 100)
    private String documentNumber;

    @Column(name = "CUIT_CUIL", length = 25)
    private String cuitCuil;

    @Column(name = "COUNTRY", length = 50)
    private String country;

    @Column(name = "ACTIVITY", length = 1000)
    private String activity;

    @Column(name = "MONTHLY_ESTIMATE")
    private BigDecimal monthlyEstimate;

    @Column(name = "LEVEL", length = 150)
    private String level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getCuitCuil() {
        return cuitCuil;
    }

    public void setCuitCuil(String cuitCuil) {
        this.cuitCuil = cuitCuil;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public BigDecimal getMonthlyEstimate() {
        return monthlyEstimate;
    }

    public void setMonthlyEstimate(BigDecimal monthlyEstimate) {
        this.monthlyEstimate = monthlyEstimate;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GridClient{");
        sb.append("id=").append(id);
        sb.append(", names='").append(names).append('\'');
        sb.append(", lastNames='").append(lastNames).append('\'');
        sb.append(", businessName='").append(businessName).append('\'');
        sb.append(", documentNumber='").append(documentNumber).append('\'');
        sb.append(", cuitCuil='").append(cuitCuil).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", activity='").append(activity).append('\'');
        sb.append(", monthlyEstimate=").append(monthlyEstimate);
        sb.append(", level='").append(level).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
