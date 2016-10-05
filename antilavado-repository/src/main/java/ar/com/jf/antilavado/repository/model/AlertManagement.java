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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * AlertManagement.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 17/03/2016.
 */
@Entity
@Table(name = "ALERT_MANAGEMENT")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AlertManagement extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ALERT_MANAGEMENT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private Client client;

    @NotNull
    @Column(name = "PERIOD_AMOUNT", nullable = false)
    private BigDecimal periodAmount;

    @NotNull
    @Column(name = "DIFFERENCE", nullable = false)
    private BigDecimal difference;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UNUSUAL_OPERATION_STATUS_TYPE_ID")
    private StatusType unusualOperation;

    /*@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UNUSUAL_RESULT_VALUES_TYPE_ID")
    private ValuesType unusualResult;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SUSPECT_OPERATION_VALUES_TYPE_ID")
    private ValuesType suspectOperation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SUSPECT_RESULT_VALUES_TYPE_ID")
    private ValuesType suspectResult;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "REPORTED_VALUES_TYPE_ID")
    private ValuesType reported;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STATUS_TYPE_ID")
    private StatusType status;*/

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "UIF_REPORT")
    private DateTime uifReport;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ASSIGNED_USER_ID")
    private User assignedUser;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "PERIOD_ANALYZED")
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private DateTime periodAnalyzed;

    @Size(min = 0, max = 100)
    @Column(name = "PERIOD_DESCRIPTION", length = 100)
    private String periodDescription;

    @Size(min = 0, max = 3000)
    @Column(name = "MONITORING", length = 3000)
    private String monitoring;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public BigDecimal getPeriodAmount() {
        return periodAmount;
    }

    public void setPeriodAmount(BigDecimal periodAmount) {
        this.periodAmount = periodAmount;
    }

    public BigDecimal getDifference() {
        return difference;
    }

    public void setDifference(BigDecimal difference) {
        this.difference = difference;
    }

    public StatusType getUnusualOperation() {
        return unusualOperation;
    }

    public void setUnusualOperation(StatusType unusualOperation) {
        this.unusualOperation = unusualOperation;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    public DateTime getPeriodAnalyzed() {
        return periodAnalyzed;
    }

    public void setPeriodAnalyzed(DateTime periodAnalyzed) {
        this.periodAnalyzed = periodAnalyzed;
    }

    public String getPeriodDescription() {
        return periodDescription;
    }

    public void setPeriodDescription(String periodDescription) {
        this.periodDescription = periodDescription;
    }

    public DateTime getUifReport() {
        return uifReport;
    }

    public void setUifReport(DateTime uifReport) {
        this.uifReport = uifReport;
    }

    public String getMonitoring() {
        return monitoring;
    }

    public void setMonitoring(String monitoring) {
        this.monitoring = monitoring;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlertManagement)) return false;
        AlertManagement that = (AlertManagement) o;
        return java.util.Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(getId());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AlertManagement{");
        sb.append("id=").append(id);
        sb.append(", client=").append(client);
        sb.append(", periodAmount=").append(periodAmount);
        sb.append(", difference=").append(difference);
        sb.append(", unusualOperation=").append(unusualOperation);
        sb.append(", uifReport=").append(uifReport);
        sb.append(", assignedUser=").append(assignedUser);
        sb.append(", periodAnalyzed=").append(periodAnalyzed);
        sb.append(", periodDescription='").append(periodDescription).append('\'');
        sb.append(", monitoring='").append(monitoring).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

