package ar.com.jf.antilavado.service.client;

import ar.com.jf.antilavado.repository.dto.response.client.BasicClient;
import ar.com.jf.antilavado.repository.interfaces.BankAccountRepository;
import ar.com.jf.antilavado.repository.interfaces.ClientRepository;
import ar.com.jf.antilavado.repository.interfaces.DomicileRepository;
import ar.com.jf.antilavado.repository.interfaces.TelephoneRepository;
import ar.com.jf.antilavado.repository.model.BankAccount;
import ar.com.jf.antilavado.repository.model.Client;
import ar.com.jf.antilavado.repository.model.Domicile;
import ar.com.jf.antilavado.repository.model.Telephone;
import ar.com.jf.antilavado.repository.model.view.ClientLevelView;
import com.google.common.base.Strings;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * ClientServiceImpl.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 14/09/2015.
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TelephoneRepository telephoneRepository;

    @Autowired
    private DomicileRepository domicileRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    @Transactional(readOnly = true)
    public Collection<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    @Transactional
    public Client add(Client client) {
        if (client.getIndividual()) {
            client.setNames(WordUtils.capitalizeFully(client.getNames()));
            client.setLastNames(WordUtils.capitalizeFully(client.getLastNames()));
        } else {
            client.setBusinessName(WordUtils.capitalizeFully(client.getBusinessName()));
        }
        if (!Strings.isNullOrEmpty(client.getEmail())) {
            client.setEmail(client.getEmail().toLowerCase());
        }

        for (BankAccount ba : client.getBankAccounts()) {
            ba.setClient(client);
            bankAccountRepository.auditSave(ba);
        }
        for (Domicile d : client.getDomiciles()) {
            d.setClient(client);
            d.getDistrict().setProvince(d.getProvince());
            d.getLocality().setProvince(d.getProvince());
            d.getLocality().setDistrict(d.getDistrict());
            domicileRepository.auditSave(d);
        }
        for (Telephone t : client.getTelephones()) {
            t.setClient(client);
            telephoneRepository.auditSave(t);
        }
        client.getEconomicFinancialProfile().getClients().add(client);

        clientRepository.auditSave(client);
        clientRepository.save(client);
        return client;
    }

    @Override
    @Transactional(readOnly = true)
    public Client get(Long id) {
        return clientRepository.get(id);
    }

    @Override
    @Transactional
    public void update(Client client) {
        if (client.getIndividual()) {
            client.setNames(WordUtils.capitalizeFully(client.getNames()));
            client.setLastNames(WordUtils.capitalizeFully(client.getLastNames()));
        } else {
            client.setBusinessName(WordUtils.capitalizeFully(client.getBusinessName()));
        }
        if (!Strings.isNullOrEmpty(client.getEmail())) {
            client.setEmail(client.getEmail().toLowerCase());
        }
        for (BankAccount ba : client.getBankAccounts()) {
            if (ba.getClient() == null) {
                ba.setClient(client);
                bankAccountRepository.auditSave(ba);
            }
            bankAccountRepository.auditUpdate(ba);
        }
        for (Domicile d : client.getDomiciles()) {
            if (d.getClient() == null) {
                d.setClient(client);
                d.getDistrict().setProvince(d.getProvince());
                d.getLocality().setProvince(d.getProvince());
                d.getLocality().setDistrict(d.getDistrict());
                domicileRepository.auditSave(d);
            }
            domicileRepository.auditUpdate(d);
        }
        for (Telephone t : client.getTelephones()) {
            if (t.getClient() == null) {
                t.setClient(client);
                telephoneRepository.auditSave(t);
            }
            telephoneRepository.auditUpdate(t);
        }
        clientRepository.auditUpdate(client);
        clientRepository.merge(client);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        clientRepository.delete(clientRepository.load(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Telephone> getTelephonesBy(Long clientId) {
        return telephoneRepository.findBy(clientId);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Domicile> getDomicilesBy(Long clientId) {
        return domicileRepository.findBy(clientId);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<BankAccount> getAccountsBy(Long clientId) {
        return bankAccountRepository.findBy(clientId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BasicClient> findBasicClientsBy(String name) {
        return clientRepository.findBasicClientsBy(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BasicClient> findBasicClientsForGeneralFileBy(String name) {
        return clientRepository.findBasicClientsForGeneralFileBy(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BasicClient> findBasicClientsForFileMovementBy(String name) {
        return clientRepository.findBasicClientsForFileMovementBy(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientLevelView> findAllGridClients() {
        return clientRepository.findAllGridClients();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientLevelView> findGridClientsBy(String level) {
        return clientRepository.findGridClientsBy(level);
    }

    @Override
    @Transactional(readOnly = true)
    public BasicClient getBasicClientBy(Long id) {
        return clientRepository.getBasicClientBy(id);
    }

}
