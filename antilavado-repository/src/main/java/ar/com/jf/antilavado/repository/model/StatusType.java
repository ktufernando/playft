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
 * StatusType.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 13/01/2016.
 */
@Entity
@Table(name = "STATUS_TYPE", uniqueConstraints = {
        @UniqueConstraint(columnNames = "DESCRIPTION") })
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StatusType implements Serializable {

    @Id
    @Column(name = "STATUS_TYPE_ID")
    private Long id;

    @NotNull
    @Size(min = 0, max = 100)
    @Column(name = "DESCRIPTION", length = 100, unique = true, nullable = false)
    private String description;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    public StatusType parent;

    @JsonIgnore
    @OneToMany(mappedBy="parent", cascade = CascadeType.ALL)
    public Set<StatusType> statusTypes = new HashSet<>(0);

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
    private Set<GeneralFile> generalFiles = new HashSet<>(0);

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unusualOperation")
    private Set<AlertManagement> alerts;

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

    public Set<StatusType> getStatusTypes() {
        return statusTypes;
    }

    public void setStatusTypes(Set<StatusType> statusTypes) {
        this.statusTypes = statusTypes;
    }

    public StatusType getParent() {
        return parent;
    }

    public void setParent(StatusType parent) {
        this.parent = parent;
    }

    public Set<GeneralFile> getGeneralFiles() {
        return generalFiles;
    }

    public void setGeneralFiles(Set<GeneralFile> generalFiles) {
        this.generalFiles = generalFiles;
    }

    public Set<AlertManagement> getAlerts() {
        return alerts;
    }

    public void setAlerts(Set<AlertManagement> alerts) {
        this.alerts = alerts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StatusType)) return false;
        StatusType societyType = (StatusType) o;
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
