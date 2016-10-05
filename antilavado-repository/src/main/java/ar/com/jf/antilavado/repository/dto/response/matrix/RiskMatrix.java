package ar.com.jf.antilavado.repository.dto.response.matrix;

import ar.com.jf.antilavado.repository.model.FactorLevel;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by ktufernando on 01/12/2015.
 */
public class RiskMatrix {

    private List<FactorData> factors;
    private FactorLevel level;
    private int totalPonderation;

    public RiskMatrix() {
        factors = Lists.newArrayList();
        totalPonderation = 0;
    }

    public void addFactorData(FactorData factorData){
        factors.add(factorData);
        plusTotalPonderation(factorData.getPonderation());
    }

    public void plusTotalPonderation(int value){
        totalPonderation = totalPonderation + value;
    }

    public List<FactorData> getFactors() {
        return factors;
    }

    public void setFactors(List<FactorData> factors) {
        this.factors = factors;
    }

    public FactorLevel getLevel() {
        return level;
    }

    public void setLevel(FactorLevel level) {
        this.level = level;
    }

    public int getTotalPonderation() {
        return totalPonderation;
    }

    public void setTotalPonderation(int totalPonderation) {
        this.totalPonderation = totalPonderation;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RiskMatrix{");
        sb.append("factors=").append(factors);
        sb.append(", level=").append(level);
        sb.append(", totalPonderation=").append(totalPonderation);
        sb.append('}');
        return sb.toString();
    }
}
