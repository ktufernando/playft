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
 * DocumentType.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 14/09/2015.
 */
@Entity
@Table(name = "DOCUMENTS_TYPES", uniqueConstraints = {
        @UniqueConstraint(columnNames = "DESCRIPTION") })
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DocumentType implements Serializable {

    @Id
    @Column(name = "DOCUMENT_TYPE_ID")
    private Long id;

    @NotNull
    @Size(min = 0, max = 10)
    @Column(name = "DESCRIPTION", length = 10, unique = true, nullable = false)
    private String description;

    @Column(name = "STATUS", nullable = false)
    private Integer status;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "documentType")
    private Set<Client> clients = new HashSet<>(0);

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DocumentType)) return false;
        DocumentType documentType = (DocumentType) o;
        return Objects.equal(getId(), documentType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DocumentType{");
        sb.append("id=").append(id);
        sb.append(", description='").append(description).append('\'');
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
