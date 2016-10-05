package ar.com.jf.antilavado.repository.interfaces;

import ar.com.jf.antilavado.repository.dto.response.client.BasicClient;
import ar.com.jf.antilavado.repository.generic.GenericRepository;
import ar.com.jf.antilavado.repository.model.Client;
import ar.com.jf.antilavado.repository.model.view.ClientLevelView;

import java.util.List;

/**
 * ClientRepository.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 14/09/2015.
 */
public interface ClientRepository extends GenericRepository<Long, Client> {

    String getAfipActivity(Long id);

    boolean isFisic(Long id);

    Long getPEP(Long id);

    Long getLUT(Long id);

    Long getSOI(Long id);

    Long getSpecialClient(Long id);

    Long getROS(Long id);

    Long getSocietyType(Long id);

    Long getOperationFrequency(Long id);

    Long getMarketExperience(Long id);

    List<BasicClient> findBasicClientsBy(String name);

    List<BasicClient> findBasicClientsForGeneralFileBy(String name);

    List<BasicClient> findBasicClientsForFileMovementBy(String name);

    List<ClientLevelView> findAllGridClients();

    List<ClientLevelView> findGridClientsBy(String level);

    List<Client> getByCuitCuil(String cuitCuil);

    BasicClient getBasicClientBy(Long id);
}
