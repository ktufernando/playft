package ar.com.jf.antilavado.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * MatrixMaster.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 19/01/2016.
 */
@Entity
@Table(name = "MATRIX_MASTER", indexes = {
        @Index(name = "MATRIX_MASTER_CLIENT_ID_INDEX", columnList = "CLIENT_ID")})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MatrixMaster extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MATRIX_MASTER_ID")
    private Long id;

    @NotNull
    @Column(name = "TOTAL_PONDERATION", nullable = false)
    private Integer totalPonderation;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FACTOR_LEVEL_ID", nullable = false)
    private FactorLevel factorLevel;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "matrixMaster", cascade = CascadeType.ALL)
    private List<MatrixDetail> matrixDetails = new ArrayList<>(0);

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalPonderation() {
        return totalPonderation;
    }

    public void setTotalPonderation(Integer totalPonderation) {
        this.totalPonderation = totalPonderation;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<MatrixDetail> getMatrixDetails() {
        return matrixDetails;
    }

    public void setMatrixDetails(List<MatrixDetail> matrixDetails) {
        this.matrixDetails = matrixDetails;
    }

    public FactorLevel getFactorLevel() {
        return factorLevel;
    }

    public void setFactorLevel(FactorLevel factorLevel) {
        this.factorLevel = factorLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatrixMaster)) return false;
        MatrixMaster district = (MatrixMaster) o;
        return Objects.equal(getId(), district.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MatrixMaster{");
        sb.append("id=").append(id);
        sb.append(", totalPonderation=").append(totalPonderation);
        sb.append('}');
        return sb.toString();
    }
}
