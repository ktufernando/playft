package ar.com.jf.antilavado.repository.model;

import com.google.common.base.Objects;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * MatrixDetail.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 19/01/2016.
 */
@Entity
@Table(name = "MATRIX_DETAIL")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MatrixDetail extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MATRIX_DETAIL_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MATRIX_MASTER_ID", nullable = false)
    private MatrixMaster matrixMaster;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DETAIL_RISK_FACTOR", nullable = false)
    private DetailRiskFactor detailRiskFactor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MatrixMaster getMatrixMaster() {
        return matrixMaster;
    }

    public void setMatrixMaster(MatrixMaster matrixMaster) {
        this.matrixMaster = matrixMaster;
    }

    public DetailRiskFactor getDetailRiskFactor() {
        return detailRiskFactor;
    }

    public void setDetailRiskFactor(DetailRiskFactor detailRiskFactor) {
        this.detailRiskFactor = detailRiskFactor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatrixDetail)) return false;
        MatrixDetail district = (MatrixDetail) o;
        return Objects.equal(getId(), district.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
