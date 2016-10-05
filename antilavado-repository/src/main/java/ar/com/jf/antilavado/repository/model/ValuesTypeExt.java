package ar.com.jf.antilavado.repository.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * ValuesTypeExt.java
 * <p>
 * PLAYFT.
 * <p>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p>
 * Created by fvaldes on 10/03/2016.
 */
@Entity
@Table(name = "VALUES_TYPE_EXT")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ValuesTypeExt implements Serializable {

    @Id
    @Column(name = "VALUES_TYPE_EXT_ID")
    private Long id;

    @NotNull
    @Column(name = "VALUE_FROM", length = 10, nullable = false)
    private Integer from;

    @NotNull
    @Column(name = "VALUE_TO", length = 10, nullable = false)
    private Integer to;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValuesTypeExt)) return false;
        ValuesTypeExt that = (ValuesTypeExt) o;
        return java.util.Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(getId());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ValuesTypeExt{");
        sb.append("id=").append(id);
        sb.append(", from=").append(from);
        sb.append(", to=").append(to);
        sb.append('}');
        return sb.toString();
    }
}
