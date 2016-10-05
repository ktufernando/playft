package ar.com.jf.antilavado.repository.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Objects;
import javassist.runtime.Inner;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Province.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 14/09/2015.
 */
@Entity
@Table(name = "PROVINCES")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Province implements Serializable {

    @Id
    @Column(name = "PROVINCE_ID")
    private Long id;

    @NotNull
    @Size(min = 0, max = 50)
    @Column(name = "DESCRIPTION", length = 50, nullable = false)
    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUNTRY_ID", nullable = false)
    private Country country;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "province")
    private Set<Locality> localities = new HashSet<>(0);

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "province")
    private Set<District> districts = new HashSet<>(0);

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "province")
    private List<Domicile> domiciles = new ArrayList<>(0);

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

    @JsonIgnore
    public Set<Locality> getLocalities() {
        return localities;
    }

    @JsonProperty
    public void setLocalities(Set<Locality> localities) {
        this.localities = localities;
    }

    @JsonIgnore
    public Set<District> getDistricts() {
        return districts;
    }

    @JsonProperty
    public void setDistricts(Set<District> districts) {
        this.districts = districts;
    }

    @JsonIgnore
    public List<Domicile> getDomiciles() {
        return domiciles;
    }
    @JsonProperty
    public void setDomiciles(List<Domicile> domiciles) {
        this.domiciles = domiciles;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Province)) return false;
        Province province = (Province) o;
        return Objects.equal(getId(), province.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Province{");
        sb.append("id=").append(id);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
