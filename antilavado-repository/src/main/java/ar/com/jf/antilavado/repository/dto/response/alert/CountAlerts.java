package ar.com.jf.antilavado.repository.dto.response.alert;

/**
 * Created by fvaldes on 07/04/2016.
 */
public class CountAlerts {

    private Long assigned;
    private Long closed;
    private Long notTreatment;
    private Long isSuspicious;

    public Long getAssigned() {
        return assigned;
    }

    public void setAssigned(Long assigned) {
        this.assigned = assigned;
    }

    public Long getClosed() {
        return closed;
    }

    public void setClosed(Long closed) {
        this.closed = closed;
    }

    public Long getNotTreatment() {
        return notTreatment;
    }

    public void setNotTreatment(Long notTreatment) {
        this.notTreatment = notTreatment;
    }

    public Long getIsSuspicious() {
        return isSuspicious;
    }

    public void setIsSuspicious(Long isSuspicious) {
        this.isSuspicious = isSuspicious;
    }
}
