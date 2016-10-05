package ar.com.jf.antilavado.repository.dto.response.matrix;

/**
 * Created by ktufernando on 01/12/2015.
 */
public class FactorData {

    private String factor;
    private String impact;
    private int ponderation;

    public FactorData(String factor, String impact, Integer ponderation) {
        this.factor = factor;
        this.impact = impact;
        this.ponderation = ponderation;
    }

    public FactorData() {
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public int getPonderation() {
        return ponderation;
    }

    public void setPonderation(int ponderation) {
        this.ponderation = ponderation;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("FactorData{");
        sb.append("factor='").append(factor).append('\'');
        sb.append(", impact='").append(impact).append('\'');
        sb.append(", ponderation=").append(ponderation);
        sb.append('}');
        return sb.toString();
    }
}
