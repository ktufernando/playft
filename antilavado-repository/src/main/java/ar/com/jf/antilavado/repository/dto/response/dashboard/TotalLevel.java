package ar.com.jf.antilavado.repository.dto.response.dashboard;

/**
 * Created by ktufernando on 16/02/2016.
 */
public class TotalLevel {

    private Integer total;
    private String level;
    private String color;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
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
