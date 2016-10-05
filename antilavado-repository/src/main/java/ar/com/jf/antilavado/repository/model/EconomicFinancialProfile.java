package ar.com.jf.antilavado.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * EconomicFinancialProfile.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 14/09/2015.
 */
@Entity
@Table(name = "ECONOMIC_FINANCIAL_PROFILES")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EconomicFinancialProfile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ECONOMIC_FINANCIAL_PROFILE_ID")
    private Long id;

    @NotNull
    @Column(name = "MONTHLY_ESTIMATE", nullable = false)
    private BigDecimal monthlyEstimate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FISCAL_SITUATION")
    private ValuesType fiscalSituation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MONOTRIBUTO_CATEGORY")
    private ValuesType monotributoCategory;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MONTHLY_INCOME_DECLARED")
    private ValuesType monthlyIncomeDeclared;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MONTHLY_INCOME_AS_EECC")
    private ValuesType monthlyIncomeAsEECC;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MONTHLY_INCOME_AS_DDJJ")
    private ValuesType monthlyIncomeAsDDJJ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MONTHLY_INCOME_AS_DDJJ2")
    private ValuesType monthlyIncomeAsDDJJ2;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "economicFinancialProfile")
    private Set<Client> clients = new HashSet<>(0);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMonthlyEstimate() {
        return monthlyEstimate;
    }

    public void setMonthlyEstimate(BigDecimal monthlyEstimate) {
        this.monthlyEstimate = monthlyEstimate;
    }

    public ValuesType getFiscalSituation() {
        return fiscalSituation;
    }

    public void setFiscalSituation(ValuesType fiscalSituation) {
        this.fiscalSituation = fiscalSituation;
    }

    public ValuesType getMonotributoCategory() {
        return monotributoCategory;
    }

    public void setMonotributoCategory(ValuesType monotributoCategory) {
        this.monotributoCategory = monotributoCategory;
    }

    public ValuesType getMonthlyIncomeDeclared() {
        return monthlyIncomeDeclared;
    }

    public void setMonthlyIncomeDeclared(ValuesType monthlyIncomeDeclared) {
        this.monthlyIncomeDeclared = monthlyIncomeDeclared;
    }

    public ValuesType getMonthlyIncomeAsEECC() {
        return monthlyIncomeAsEECC;
    }

    public void setMonthlyIncomeAsEECC(ValuesType monthlyIncomeAsEECC) {
        this.monthlyIncomeAsEECC = monthlyIncomeAsEECC;
    }

    public ValuesType getMonthlyIncomeAsDDJJ() {
        return monthlyIncomeAsDDJJ;
    }

    public void setMonthlyIncomeAsDDJJ(ValuesType monthlyIncomeAsDDJJ) {
        this.monthlyIncomeAsDDJJ = monthlyIncomeAsDDJJ;
    }

    public ValuesType getMonthlyIncomeAsDDJJ2() {
        return monthlyIncomeAsDDJJ2;
    }

    public void setMonthlyIncomeAsDDJJ2(ValuesType monthlyIncomeAsDDJJ2) {
        this.monthlyIncomeAsDDJJ2 = monthlyIncomeAsDDJJ2;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EconomicFinancialProfile)) return false;
        EconomicFinancialProfile that = (EconomicFinancialProfile) o;
        return Objects.equal(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EconomicFinancialProfile{");
        sb.append("id=").append(id);
        sb.append(", monthlyEstimate=").append(monthlyEstimate);
        sb.append(", fiscalSituation=").append(fiscalSituation);
        sb.append(", monotributoCategory=").append(monotributoCategory);
        sb.append(", monthlyIncomeDeclared=").append(monthlyIncomeDeclared);
        sb.append(", monthlyIncomeAsEECC=").append(monthlyIncomeAsEECC);
        sb.append(", monthlyIncomeAsDDJJ=").append(monthlyIncomeAsDDJJ);
        sb.append(", monthlyIncomeAsDDJJ2=").append(monthlyIncomeAsDDJJ2);
        sb.append('}');
        return sb.toString();
    }
}
