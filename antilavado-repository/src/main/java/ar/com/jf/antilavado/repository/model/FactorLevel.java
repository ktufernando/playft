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
 * FactorLevel.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 17/11/2015.
 */
@Entity
@Table(name = "FACTOR_LEVELS")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FactorLevel extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FACTOR_LEVELS_ID")
    private Long id;

    @NotNull
    @Size(min = 0, max = 150)
    @Column(name = "DESCRIPTION", length = 150, nullable = false, unique = true)
    private String description;

    @NotNull
    @Size(min = 7, max = 7)
    @Column(name = "COLOR", length = 10, nullable = false, unique = true)
    private String color;

    @NotNull
    @Column(name = "LOWER_BOUND", precision = 12, scale = 2, nullable = false)
    private BigDecimal lowerBound;

    @NotNull
    @Column(name = "UPPER_BOUND", precision = 12, scale = 2, nullable = false)
    private BigDecimal upperBound;

    @NotNull
    @Column(name = "PERCENTAGE", precision = 2, nullable = false)
    private int percentage;

    @Column(name = "ACTIVE", nullable = false)
    private boolean active;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "factorLevel")
    private Set<MatrixMaster> matrixMasters;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(BigDecimal lowerBound) {
        this.lowerBound = lowerBound;
    }

    public BigDecimal getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(BigDecimal upperBound) {
        this.upperBound = upperBound;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public Set<MatrixMaster> getMatrixMasters() {
        return matrixMasters;
    }

    public void setMatrixMasters(Set<MatrixMaster> matrixMasters) {
        this.matrixMasters = matrixMasters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FactorLevel)) return false;
        FactorLevel that = (FactorLevel) o;
        return Objects.equal(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FactorLevel{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", color=" + color +
                ", lowerBound=" + lowerBound +
                ", upperBound=" + upperBound +
                ", percentage=" + percentage +
                ", active=" + active +
                '}';
    }
}
