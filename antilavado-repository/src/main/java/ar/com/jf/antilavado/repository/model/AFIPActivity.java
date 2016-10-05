package ar.com.jf.antilavado.repository.model;

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
 * AFIPActivity.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 14/09/2015.
 */
@Entity
@Table(name = "AFIP_ACTIVITIES", indexes={
        @Index(name="AFIP_ACTIVITIES_CODE_ID_INDEX", columnList="CODE"),
        @Index(name="AFIP_ACTIVITIES_PARENT_ID_INDEX", columnList="PARENT")})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AFIPActivity implements Serializable {

    @Id
    @Column(name = "AFIP_ACTIVITIES_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 0, max = 6)
    @Column(name = "CODE", length = 3, nullable = false)
    private String code;

    @NotNull
    @Size(min = 0, max = 650)
    @Column(name = "DESCRIPTION", length = 650, nullable = false)
    private String description;

    @Size(min = 0, max = 3)
    @Column(name = "PARENT", length = 3)
    private String parent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AFIPActivity)) return false;
        AFIPActivity that = (AFIPActivity) o;
        return Objects.equal(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AFIPActivity{");
        sb.append("id=").append(id);
        sb.append(", code='").append(code).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", parent='").append(parent).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
