package ar.com.jf.antilavado.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * DetailRiskFactor.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 15/11/2015.
 */
@Entity
@Table(name = "DETAIL_RISK_FACTOR", indexes={
        @Index(name="DETAIL_RISK_FACTOR_DESCRIPTION", columnList="DESCRIPTION")})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DetailRiskFactor extends AbstractAuditingEntity implements Serializable {

    @Id
    @Column(name = "DETAIL_RISK_FACTOR_ID")
    private Long id;

    @NotNull
    @Size(min = 0, max = 1000)
    @Column(name = "DESCRIPTION", length = 150, nullable = false)
    private String description;

    @Column(name = "ACTIVE", nullable = false)
    private boolean active;

    @NotNull
    @Column(name = "PONDERATION", nullable = false)
    private Integer ponderation;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "MASTER_RISK_FACTOR_ID", nullable = false)
    private MasterRiskFactor masterRiskFactor;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "VALUES_TYPE_ID", nullable = true)
    private ValuesType valuesType;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "detailRiskFactor")
    private Set<MatrixDetail> matrixDetails = new HashSet<>(0);


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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getPonderation() {
        return ponderation;
    }

    public void setPonderation(Integer ponderation) {
        this.ponderation = ponderation;
    }

    public MasterRiskFactor getMasterRiskFactor() {
        return masterRiskFactor;
    }

    public void setMasterRiskFactor(MasterRiskFactor masterRiskFactor) {
        this.masterRiskFactor = masterRiskFactor;
    }

    public ValuesType getValuesType() {
        return valuesType;
    }

    public void setValuesType(ValuesType valuesType) {
        this.valuesType = valuesType;
    }

    public Set<MatrixDetail> getMatrixDetails() {
        return matrixDetails;
    }

    public void setMatrixDetails(Set<MatrixDetail> matrixDetails) {
        this.matrixDetails = matrixDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetailRiskFactor that = (DetailRiskFactor) o;
        return Objects.equal(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DetailRiskFactor{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", active=" + active +
                ", ponderation=" + ponderation +
                '}';
    }
}
