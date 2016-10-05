package ar.com.jf.antilavado.repository.dto.response.matrix;

/**
 * Created by ktufernando on 19/01/2016.
 */
public class RiskMatrixCron {

    private RiskMatrix riskMatrix;
    private String errors;

    public RiskMatrix getRiskMatrix() {
        return riskMatrix;
    }

    public void setRiskMatrix(RiskMatrix riskMatrix) {
        this.riskMatrix = riskMatrix;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }
}
