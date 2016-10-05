package ar.com.jf.antilavado.rest.endpoint;

import ar.com.jf.antilavado.repository.dto.response.client.BasicClient;
import ar.com.jf.antilavado.repository.model.BankAccount;
import ar.com.jf.antilavado.repository.model.Client;
import ar.com.jf.antilavado.repository.model.Domicile;
import ar.com.jf.antilavado.repository.model.Telephone;
import ar.com.jf.antilavado.repository.model.view.ClientLevelView;
import ar.com.jf.antilavado.rest.annotation.PerformanceLog;
import ar.com.jf.antilavado.service.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * ClientRest.java
 * <p>
 * PLAYFT.
 * <p>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p>
 * Created by fvaldes on 14/09/2015.
 */
@RestController
@RequestMapping(value = "/user")
public class ClientRest {

    @Autowired
    private ClientService clientService;

    @PerformanceLog
    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public
    @ResponseBody
    Collection<Client> getAll() {
        return this.clientService.getAll();
    }

    @PerformanceLog
    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public
    @ResponseBody
    Client add(@RequestBody Client client) {
        return this.clientService.add(client);
    }

    @PerformanceLog
    @RequestMapping(value = "/clients/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Client get(@PathVariable Long id) {
        return this.clientService.get(id);
    }

    @PerformanceLog
    @RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void update(@RequestBody Client client) {
        this.clientService.update(client);
    }

    @PerformanceLog
    @RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteProgram(@PathVariable Long id) {
        this.clientService.delete(id);
    }

    @PerformanceLog
    @RequestMapping(value = "/clients/{id}/telephones", method = RequestMethod.GET)
    public
    @ResponseBody
    Collection<Telephone> getTelephonesBy(@PathVariable Long id) {
        return this.clientService.getTelephonesBy(id);
    }

    @PerformanceLog
    @RequestMapping(value = "/clients/{id}/domiciles", method = RequestMethod.GET)
    public
    @ResponseBody
    Collection<Domicile> getDomicilesBy(@PathVariable Long id) {
        return this.clientService.getDomicilesBy(id);
    }

    @PerformanceLog
    @RequestMapping(value = "/clients/{id}/accounts", method = RequestMethod.GET)
    public
    @ResponseBody
    Collection<BankAccount> getAccountsBy(@PathVariable Long id) {
        return this.clientService.getAccountsBy(id);
    }

    @PerformanceLog
    @RequestMapping(value = "/clients/by/{name}", method = RequestMethod.GET)
    public
    @ResponseBody
    Collection<BasicClient> findClientsByNames(@PathVariable String name) {
        return this.clientService.findBasicClientsBy(name);
    }

    @PerformanceLog
    @RequestMapping(value = "/client/by/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    BasicClient getClientById(@PathVariable Long id) {
        return this.clientService.getBasicClientBy(id);
    }

    @PerformanceLog
    @RequestMapping(value = "/clients/grid", method = RequestMethod.GET)
    public
    @ResponseBody
    List<ClientLevelView> findAllGridClients() {
        return this.clientService.findAllGridClients();
    }

    @PerformanceLog
    @RequestMapping(value = "/clients/grid/{level}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<ClientLevelView> findGridClientsBy(@PathVariable String level) {
        return this.clientService.findGridClientsBy(level);
    }
}
