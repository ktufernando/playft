package ar.com.jf.antilavado.repository.model;

import ar.com.jf.antilavado.repository.model.util.CustomDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Objects;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Client.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 14/09/2015.
 */
@Entity
@Table(name = "CLIENTS")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Client extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CLIENT_ID")
    private Long id;

    @Size(min = 0, max = 100)
    @Column(name = "NAMES", length = 100)
    private String names;

    @Size(min = 0, max = 100)
    @Column(name = "LAST_NAMES", length = 100)
    private String lastNames;

    @Size(min = 0, max = 100)
    @Column(name = "BUSINESS_NAME", length = 100)
    private String businessName;

    @NotNull
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "BIRTHDAY", nullable = false)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private DateTime birthday;

    @Size(min = 0, max = 25)
    @Column(name = "DOCUMENT_NUMBER", length = 25)
    private String documentNumber;

    @NotNull
    @Size(min = 0, max = 25)
    @Column(name = "CUIT_CUIL", length = 25, nullable = false)
    private String cuitCuil;

    @Size(min = 7, max = 50)
    @Column(name = "EMAIL", length = 50)
    private String email;

    @NotNull
    @Size(min = 0, max = 10)
    @Column(name = "AFIP_ACTIVITY", length = 10, nullable = false)
    private String afipActivity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROS_VALUES_TYPE_ID")
    private ValuesType ros;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LUT_VALUES_TYPE_ID")
    private ValuesType lut;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SOI_VALUES_TYPE_ID")
    private ValuesType soi;

    @Column(name = "NOSIS")
    private Boolean nosis;

    @Column(name = "WORLDSYS")
    private Boolean worldsys;

    @Column(name = "PUBLIC_SERVICE")
    private Boolean publicService;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PEP_VALUES_TYPE_ID")
    private ValuesType pep;

    @Column(name = "AFFIDAVIT_HOME")
    private Boolean affidavitHome;

    @Column(name = "AFIP_CONST")
    private Boolean afipConst;

    @NotNull
    @Column(name = "IS_INDIVIDUAL", nullable = false)
    private Boolean individual;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SPECIAL_CLIENT_VALUES_TYPE_ID")
    private ValuesType specialClient;

    @Column(name = "STATUS")
    private Integer status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SOCIETY_TYPE_VALUES_TYPE_ID")
    private ValuesType societyType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COUNTRY_ID")
    private Country country;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DOCUMENT_TYPE_ID", nullable = true)
    private DocumentType documentType;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ECONOMIC_FINANCIAL_PROFILE_ID", nullable = false)
    private EconomicFinancialProfile economicFinancialProfile;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OPERATION_FREQUENCY_TYPE_VALUES_TYPE_ID")
    private ValuesType operationFrequency;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MARKET_EXPERIENCE_TYPE_VALUES_TYPE_ID")
    private ValuesType marketExperience;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
    private Set<BankAccount> bankAccounts = new HashSet<>(0);

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Domicile> domiciles = new HashSet<>(0);

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
    private List<Telephone> telephones = new ArrayList<>(0);

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
    private Set<GeneralFile> generalFiles = new HashSet<>(0);

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
    private Set<FileMovement> filesMovement = new HashSet<>(0);

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
    private Set<MatrixMaster> matrixMasters = new HashSet<>(0);

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
    private Set<MatrixErrorLog> matrixErrorLogs = new HashSet<>(0);

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
    private Set<ClientTransaction> clientTransactions = new HashSet<>(0);

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

    public DateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(DateTime birthday) {
        this.birthday = birthday;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAfipActivity() {
        return afipActivity;
    }

    public void setAfipActivity(String afipActivity) {
        this.afipActivity = afipActivity;
    }

    public ValuesType getRos() {
        return ros;
    }

    public void setRos(ValuesType ros) {
        this.ros = ros;
    }

    public ValuesType getLut() {
        return lut;
    }

    public void setLut(ValuesType lut) {
        this.lut = lut;
    }

    public ValuesType getSoi() {
        return soi;
    }

    public void setSoi(ValuesType soi) {
        this.soi = soi;
    }

    public Boolean getNosis() {
        return nosis;
    }

    public void setNosis(Boolean nosis) {
        this.nosis = nosis;
    }

    public Boolean getWorldsys() {
        return worldsys;
    }

    public void setWorldsys(Boolean worldsys) {
        this.worldsys = worldsys;
    }

    public Boolean getPublicService() {
        return publicService;
    }

    public void setPublicService(Boolean publicService) {
        this.publicService = publicService;
    }

    public ValuesType getPep() {
        return pep;
    }

    public void setPep(ValuesType pep) {
        this.pep = pep;
    }

    public Boolean getAffidavitHome() {
        return affidavitHome;
    }

    public void setAffidavitHome(Boolean affidavitHome) {
        this.affidavitHome = affidavitHome;
    }

    public Boolean getAfipConst() {
        return afipConst;
    }

    public void setAfipConst(Boolean afipConst) {
        this.afipConst = afipConst;
    }

    public Boolean getIndividual() {
        return individual;
    }

    public void setIndividual(Boolean individual) {
        this.individual = individual;
    }

    public ValuesType getSpecialClient() {
        return specialClient;
    }

    public void setSpecialClient(ValuesType specialClient) {
        this.specialClient = specialClient;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ValuesType getSocietyType() {
        return societyType;
    }

    public void setSocietyType(ValuesType societyType) {
        this.societyType = societyType;
    }

    public ValuesType getOperationFrequency() {
        return operationFrequency;
    }

    public void setOperationFrequency(ValuesType operationFrequency) {
        this.operationFrequency = operationFrequency;
    }

    public ValuesType getMarketExperience() {
        return marketExperience;
    }

    public void setMarketExperience(ValuesType marketExperience) {
        this.marketExperience = marketExperience;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    @JsonIgnore
    public Set<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    @JsonProperty
    public void setBankAccounts(Set<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public EconomicFinancialProfile getEconomicFinancialProfile() {
        return economicFinancialProfile;
    }

    public void setEconomicFinancialProfile(EconomicFinancialProfile economicFinancialProfile) {
        this.economicFinancialProfile = economicFinancialProfile;
    }

    @JsonIgnore
    public Set<Domicile> getDomiciles() {
        return domiciles;
    }

    @JsonProperty
    public void setDomiciles(Set<Domicile> domiciles) {
        this.domiciles = domiciles;
    }

    @JsonIgnore
    public Set<GeneralFile> getGeneralFiles() {
        return generalFiles;
    }

    @JsonProperty
    public void setGeneralFiles(Set<GeneralFile> generalFiles) {
        this.generalFiles = generalFiles;
    }

    @JsonIgnore
    public Set<FileMovement> getFilesMovement() {
        return filesMovement;
    }

    @JsonProperty
    public void setFilesMovement(Set<FileMovement> filesMovement) {
        this.filesMovement = filesMovement;
    }

    @JsonIgnore
    public List<Telephone> getTelephones() {
        return telephones;
    }

    @JsonProperty
    public void setTelephones(List<Telephone> telephones) {
        this.telephones = telephones;
    }

    @JsonIgnore
    public Set<MatrixMaster> getMatrixMasters() {
        return matrixMasters;
    }

    @JsonProperty
    public void setMatrixMasters(Set<MatrixMaster> matrixMasters) {
        this.matrixMasters = matrixMasters;
    }

    @JsonIgnore
    public Set<MatrixErrorLog> getMatrixErrorLogs() {
        return matrixErrorLogs;
    }

    @JsonProperty
    public void setMatrixErrorLogs(Set<MatrixErrorLog> matrixErrorLogs) {
        this.matrixErrorLogs = matrixErrorLogs;
    }

    public Set<ClientTransaction> getClientTransactions() {
        return clientTransactions;
    }

    public void setClientTransactions(Set<ClientTransaction> clientTransactions) {
        this.clientTransactions = clientTransactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return Objects.equal(getId(), client.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Client{");
        sb.append("status=").append(status);
        sb.append(", id=").append(id);
        sb.append(", names='").append(names).append('\'');
        sb.append(", lastNames='").append(lastNames).append('\'');
        sb.append(", businessName='").append(businessName).append('\'');
        sb.append(", birthday=").append(birthday);
        sb.append(", documentNumber='").append(documentNumber).append('\'');
        sb.append(", cuitCuil='").append(cuitCuil).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", afipActivity='").append(afipActivity).append('\'');
        sb.append(", ros=").append(ros);
        sb.append(", lut=").append(lut);
        sb.append(", soi=").append(soi);
        sb.append(", nosis=").append(nosis);
        sb.append(", worldsys=").append(worldsys);
        sb.append(", publicService=").append(publicService);
        sb.append(", pep=").append(pep);
        sb.append(", affidavitHome=").append(affidavitHome);
        sb.append(", afipConst=").append(afipConst);
        sb.append(", individual=").append(individual);
        sb.append(", specialClient=").append(specialClient);
        sb.append('}');
        return sb.toString();
    }
}
