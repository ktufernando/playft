package ar.com.jf.antilavado.repository.dto.response.client;

import ar.com.jf.antilavado.repository.model.Client;

/**
 * Created by fvaldes on 13/01/2016.
 */
public class BasicClient {

    private Long id;

    private String names;

    private String lastNames;

    private String businessName;

    private Boolean individual;

    public BasicClient() {
    }

    public BasicClient(Client client) {
        this.names = client.getNames();
        this.lastNames = client.getLastNames();
        this.businessName = client.getBusinessName();
        this.individual = client.getIndividual();
    }

    public String getFinalName(){
        if (individual){
            return names + " " + lastNames;
        }
        return businessName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Boolean getIndividual() {
        return individual;
    }

    public void setIndividual(Boolean individual) {
        this.individual = individual;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BasicClient{");
        sb.append("id=").append(id);
        sb.append(", names='").append(names).append('\'');
        sb.append(", lastNames='").append(lastNames).append('\'');
        sb.append(", businessName='").append(businessName).append('\'');
        sb.append(", individual=").append(individual);
        sb.append('}');
        return sb.toString();
    }
}
