package ar.com.jf.antilavado.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * ValuesType.java
 * <p>
 * PLAYFT.
 * <p>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p>
 * Created by fvaldes on 14/02/2016.
 */
@Entity
@Table(name = "VALUES_TYPE", uniqueConstraints = {
        @UniqueConstraint(columnNames = "DESCRIPTION")})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ValuesType implements Serializable {

    @Id
    @Column(name = "VALUES_TYPE_ID")
    private Long id;

    @NotNull
    @Size(min = 0, max = 100)
    @Column(name = "DESCRIPTION", length = 100, nullable = false)
    private String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    public ValuesType parent;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ValuesTypeExt valuesTypeExt;

    @JsonIgnore
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    public Set<ValuesType> statusTypes;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "soi")
    private Set<Client> soiClients;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pep")
    private Set<Client> pepClients;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lut")
    private Set<Client> lutClients;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ros")
    private Set<Client> rosClients;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "specialClient")
    private Set<Client> specialClientClients;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "societyType")
    private Set<Client> societyTypeClients;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "operationFrequency")
    private Set<Client> operationFrequencyClients;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "marketExperience")
    private Set<Client> marketExperienceClients;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fiscalSituation")
    private Set<EconomicFinancialProfile> fiscalSituationEconomicFinancialProfile;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "monotributoCategory")
    private Set<EconomicFinancialProfile> monotributoCategoryEconomicFinancialProfile;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "monthlyIncomeDeclared")
    private Set<EconomicFinancialProfile> monthlyIncomeDeclaredEconomicFinancialProfile;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "monthlyIncomeAsEECC")
    private Set<EconomicFinancialProfile> monthlyIncomeAsEECCEconomicFinancialProfile;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "monthlyIncomeAsDDJJ")
    private Set<EconomicFinancialProfile> monthlyIncomeAsDDJJEconomicFinancialProfile;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "monthlyIncomeAsDDJJ2")
    private Set<EconomicFinancialProfile> monthlyIncomeAsDDJJ2EconomicFinancialProfile;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "valuesType")
    private Set<DetailRiskFactor> detailRiskFactors;

    public Set<Client> getOperationFrequencyClients() {
        return operationFrequencyClients;
    }

    public void setOperationFrequencyClients(Set<Client> operationFrequencyClients) {
        this.operationFrequencyClients = operationFrequencyClients;
    }

    public Set<Client> getMarketExperienceClients() {
        return marketExperienceClients;
    }

    public void setMarketExperienceClients(Set<Client> marketExperienceClients) {
        this.marketExperienceClients = marketExperienceClients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ValuesType> getStatusTypes() {
        return statusTypes;
    }

    public void setStatusTypes(Set<ValuesType> statusTypes) {
        this.statusTypes = statusTypes;
    }

    public ValuesType getParent() {
        return parent;
    }

    public void setParent(ValuesType parent) {
        this.parent = parent;
    }

    public Set<Client> getSoiClients() {
        return soiClients;
    }

    public void setSoiClients(Set<Client> soiClients) {
        this.soiClients = soiClients;
    }

    public Set<EconomicFinancialProfile> getFiscalSituationEconomicFinancialProfile() {
        return fiscalSituationEconomicFinancialProfile;
    }

    public void setFiscalSituationEconomicFinancialProfile(Set<EconomicFinancialProfile> fiscalSituationEconomicFinancialProfile) {
        this.fiscalSituationEconomicFinancialProfile = fiscalSituationEconomicFinancialProfile;
    }

    public Set<EconomicFinancialProfile> getMonotributoCategoryEconomicFinancialProfile() {
        return monotributoCategoryEconomicFinancialProfile;
    }

    public void setMonotributoCategoryEconomicFinancialProfile(Set<EconomicFinancialProfile> monotributoCategoryEconomicFinancialProfile) {
        this.monotributoCategoryEconomicFinancialProfile = monotributoCategoryEconomicFinancialProfile;
    }

    public Set<EconomicFinancialProfile> getMonthlyIncomeDeclaredEconomicFinancialProfile() {
        return monthlyIncomeDeclaredEconomicFinancialProfile;
    }

    public void setMonthlyIncomeDeclaredEconomicFinancialProfile(Set<EconomicFinancialProfile> monthlyIncomeDeclaredEconomicFinancialProfile) {
        this.monthlyIncomeDeclaredEconomicFinancialProfile = monthlyIncomeDeclaredEconomicFinancialProfile;
    }

    public Set<EconomicFinancialProfile> getMonthlyIncomeAsEECCEconomicFinancialProfile() {
        return monthlyIncomeAsEECCEconomicFinancialProfile;
    }

    public void setMonthlyIncomeAsEECCEconomicFinancialProfile(Set<EconomicFinancialProfile> monthlyIncomeAsEECCEconomicFinancialProfile) {
        this.monthlyIncomeAsEECCEconomicFinancialProfile = monthlyIncomeAsEECCEconomicFinancialProfile;
    }

    public Set<EconomicFinancialProfile> getMonthlyIncomeAsDDJJEconomicFinancialProfile() {
        return monthlyIncomeAsDDJJEconomicFinancialProfile;
    }

    public void setMonthlyIncomeAsDDJJEconomicFinancialProfile(Set<EconomicFinancialProfile> monthlyIncomeAsDDJJEconomicFinancialProfile) {
        this.monthlyIncomeAsDDJJEconomicFinancialProfile = monthlyIncomeAsDDJJEconomicFinancialProfile;
    }

    public Set<DetailRiskFactor> getDetailRiskFactors() {
        return detailRiskFactors;
    }

    public void setDetailRiskFactors(Set<DetailRiskFactor> detailRiskFactors) {
        this.detailRiskFactors = detailRiskFactors;
    }

    public Set<EconomicFinancialProfile> getMonthlyIncomeAsDDJJ2EconomicFinancialProfile() {
        return monthlyIncomeAsDDJJ2EconomicFinancialProfile;
    }

    public void setMonthlyIncomeAsDDJJ2EconomicFinancialProfile(Set<EconomicFinancialProfile> monthlyIncomeAsDDJJ2EconomicFinancialProfile) {
        this.monthlyIncomeAsDDJJ2EconomicFinancialProfile = monthlyIncomeAsDDJJ2EconomicFinancialProfile;
    }

    public Set<Client> getPepClients() {
        return pepClients;
    }

    public void setPepClients(Set<Client> pepClients) {
        this.pepClients = pepClients;
    }

    public Set<Client> getLutClients() {
        return lutClients;
    }

    public void setLutClients(Set<Client> lutClients) {
        this.lutClients = lutClients;
    }

    public Set<Client> getRosClients() {
        return rosClients;
    }

    public void setRosClients(Set<Client> rosClients) {
        this.rosClients = rosClients;
    }

    public Set<Client> getSpecialClientClients() {
        return specialClientClients;
    }

    public void setSpecialClientClients(Set<Client> specialClientClients) {
        this.specialClientClients = specialClientClients;
    }

    public Set<Client> getSocietyTypeClients() {
        return societyTypeClients;
    }

    public ValuesTypeExt getValuesTypeExt() {
        return valuesTypeExt;
    }

    public void setValuesTypeExt(ValuesTypeExt valuesTypeExt) {
        this.valuesTypeExt = valuesTypeExt;
    }

    public void setSocietyTypeClients(Set<Client> societyTypeClients) {
        this.societyTypeClients = societyTypeClients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValuesType)) return false;
        ValuesType societyType = (ValuesType) o;
        return Objects.equal(getId(), societyType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SocietyType{");
        sb.append("id=").append(id);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
