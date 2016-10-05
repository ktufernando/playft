package ar.com.jf.antilavado.repository.dto.response.client.transaction;

import ar.com.jf.antilavado.repository.model.Client;

import java.math.BigDecimal;

/**
 * Created by fvaldes on 05/04/2016.
 */
public class SumOfTransaccions {

    private Client client;
    private BigDecimal sumAmounts;

    public BigDecimal getSumAmounts() {
        return sumAmounts;
    }

    public void setSumAmounts(BigDecimal sumAmounts) {
        this.sumAmounts = sumAmounts;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
