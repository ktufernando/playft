package ar.com.jf.antilavado.service.client;

import ar.com.jf.antilavado.repository.dto.response.client.BasicClient;
import ar.com.jf.antilavado.repository.model.BankAccount;
import ar.com.jf.antilavado.repository.model.Client;
import ar.com.jf.antilavado.repository.model.Domicile;
import ar.com.jf.antilavado.repository.model.Telephone;
import ar.com.jf.antilavado.repository.model.view.ClientLevelView;

import java.util.Collection;
import java.util.List;

/**
 * ClientService.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 14/09/2015.
 */
public interface ClientService {

    Collection<Client> getAll();

    Client add(Client client);

    Client get(Long id);

    void update(Client client);

    void delete(Long id);

    Collection<Telephone> getTelephonesBy(Long clientId);

    Collection<Domicile> getDomicilesBy(Long clientId);

    Collection<BankAccount> getAccountsBy(Long clientId);

    List<BasicClient> findBasicClientsBy(String name);

    List<BasicClient> findBasicClientsForGeneralFileBy(String name);

    List<BasicClient> findBasicClientsForFileMovementBy(String name);

    List<ClientLevelView> findAllGridClients();

    List<ClientLevelView> findGridClientsBy(String level);

    BasicClient getBasicClientBy(Long id);
}
