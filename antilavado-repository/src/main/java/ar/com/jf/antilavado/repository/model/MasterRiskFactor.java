package ar.com.jf.antilavado.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
 * MasterRiskFactor.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 15/11/2015.
 */
@Entity
@Table(name = "MASTER_RISK_FACTOR")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MasterRiskFactor extends AbstractAuditingEntity implements Serializable {

    @Id
    @Column(name = "MASTER_RISK_FACTOR_ID")
    private Long id;

    @NotNull
    @Size(min = 0, max = 150)
    @Column(name = "DESCRIPTION", length = 150, nullable = false)
    private String description;

    @Column(name = "ACTIVE", nullable = false)
    private Boolean active;

    @NotNull
    @Column(name = "PONDERATION", nullable = false)
    private Integer ponderation;

    /*@NotNull
    @Size(min = 0, max = 50)
    @Column(name = "MAP_TABLE", length = 50, nullable = false)
    private String mapTable;

    @NotNull
    @Size(min = 0, max = 100)
    @Column(name = "MAP_COLUMN", length = 100, nullable = false)
    private String mapColumn;*/

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "masterRiskFactor", cascade = CascadeType.ALL)
    private Set<DetailRiskFactor> detailRiskFactors = new HashSet<>(0);


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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getPonderation() {
        return ponderation;
    }

    public void setPonderation(Integer ponderation) {
        this.ponderation = ponderation;
    }

    @JsonIgnore
    public Set<DetailRiskFactor> getDetailRiskFactors() {
        return detailRiskFactors;
    }

    @JsonProperty
    public void setDetailRiskFactors(Set<DetailRiskFactor> detailRiskFactors) {
        this.detailRiskFactors = detailRiskFactors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MasterRiskFactor that = (MasterRiskFactor) o;
        return Objects.equal(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MasterRiskFactor{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", active=" + active +
                ", ponderation=" + ponderation +
                '}';
    }
}
