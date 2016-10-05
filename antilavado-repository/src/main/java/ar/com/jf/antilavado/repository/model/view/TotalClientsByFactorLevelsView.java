package ar.com.jf.antilavado.repository.model.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * TotalClientsByFactorLevelsView.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 17/02/16.
 */
@Entity
@Table(name = "VW_TOTAL_CLIENTS_BY_FACTOR_LEVELS")
public class TotalClientsByFactorLevelsView implements Serializable{

    @Column(name = "TOTAL")
    private Long total;

    @Id
    @Column(name = "FACTOR_LEVEL", length = 150)
    private String level;

    @Column(name = "FACTOR_COLOR", length = 7)
    private String color;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
