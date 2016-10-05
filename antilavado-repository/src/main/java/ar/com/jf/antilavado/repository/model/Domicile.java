package ar.com.jf.antilavado.repository.model;

import com.fasterxml.jackson.annotation.*;
import com.google.common.base.Objects;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Domicile.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 14/09/2015.
 */
@Entity
@Table(name = "DOMICILES")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Domicile extends AbstractAuditingEntity implements Serializable {

    public Domicile() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DOMICILE_ID")
    private Long id;

    @NotNull
    @Size(min = 0, max = 100)
    @Column(name = "STREET", length = 100, nullable = false)
    private String street;

    @NotNull
    @Column(name = "NUMBER", nullable = false)
    private Integer number;

    @Size(min = 0, max = 2)
    @Column(name = "FLOOR", length = 2)
    private String floor;

    @Size(min = 0, max = 4)
    @Column(name = "DEPARTMENT", length = 4)
    private String department;

    @Size(min = 0, max = 10)
    @Column(name = "POSTAL_CODE", length = 10)
    private String postalCode;

    @Column(name = "REAL_DOMICILE")
    private Boolean realDomicile;

    @Column(name = "LEGAL_DOMICILE")
    private Boolean legalDomicile;

    @Column(name = "ACTIVE")
    private boolean active;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DISTRICT_ID", nullable = false)
    private District district;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LOCALITY_ID", nullable = false)
    private Locality locality;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PROVINCE_ID", nullable = false)
    private Province province;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COUNTRY_ID", nullable = false)
    private Country country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Boolean getLegalDomicile() {
        return legalDomicile;
    }

    public void setLegalDomicile(Boolean legalDomicile) {
        this.legalDomicile = legalDomicile;
    }

    public Boolean getRealDomicile() {
        return realDomicile;
    }

    public void setRealDomicile(Boolean realDomicile) {
        this.realDomicile = realDomicile;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Locality getLocality() {
        return locality;
    }

    public void setLocality(Locality locality) {
        this.locality = locality;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Domicile)) return false;
        Domicile domicile = (Domicile) o;
        return Objects.equal(getId(), domicile.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Domicile{");
        sb.append("id=").append(id);
        sb.append(", street='").append(street).append('\'');
        sb.append(", number=").append(number);
        sb.append(", floor='").append(floor).append('\'');
        sb.append(", department='").append(department).append('\'');
        sb.append(", postalCode='").append(postalCode).append('\'');
        sb.append(", realDomicile=").append(realDomicile);
        sb.append(", legalDomicile=").append(legalDomicile);
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }
}
