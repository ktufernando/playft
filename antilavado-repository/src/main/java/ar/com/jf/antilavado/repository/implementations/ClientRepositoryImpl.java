package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.dto.response.client.BasicClient;
import ar.com.jf.antilavado.repository.generic.AbstractGenericRepository;
import ar.com.jf.antilavado.repository.interfaces.ClientRepository;
import ar.com.jf.antilavado.repository.model.Client;
import ar.com.jf.antilavado.repository.model.view.ClientLevelView;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClientRepositoryImpl.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 26/07/2015.
 */
@Repository
public class ClientRepositoryImpl extends AbstractGenericRepository<Long, Client> implements ClientRepository {

    @Override
    public Class getDomainClass() {
        return Client.class;
    }

    @Override
    public String getAfipActivity(Long id) {
        return (String) getSession().createQuery("SELECT c.afipActivity FROM Client c where c.id = :id")
                .setLong("id", id).uniqueResult();
    }

    @Override
    public boolean isFisic(Long id) {
        return (boolean) getSession().createQuery("SELECT c.individual FROM Client c where c.id = :id")
                .setLong("id", id).uniqueResult();
    }

    @Override
    public Long getPEP(Long id) {
        return (Long) getSession().createQuery("SELECT s.id FROM Client c JOIN c.pep s where c.id = :id")
                .setLong("id", id).uniqueResult();
    }

    @Override
    public Long getLUT(Long id) {
        return (Long) getSession().createQuery("SELECT s.id FROM Client c JOIN c.lut s where c.id = :id")
                .setLong("id", id).uniqueResult();
    }

    @Override
    public Long getSOI(Long id) {
        return (Long) getSession().createQuery("SELECT s.id FROM Client c JOIN c.soi s where c.id = :id")
                .setLong("id", id).uniqueResult();
    }

    @Override
    public Long getSpecialClient(Long id) {
        return (Long) getSession().createQuery("SELECT s.id FROM Client c JOIN c.specialClient s where c.id = :id")
                .setLong("id", id).uniqueResult();
    }

    @Override
    public Long getROS(Long id) {
        return (Long) getSession().createQuery("SELECT s.id FROM Client c JOIN c.ros s where c.id = :id")
                .setLong("id", id).uniqueResult();
    }

    @Override
    public Long getSocietyType(Long id) {
        return (Long) getSession().createQuery("SELECT s.id FROM Client c JOIN c.societyType s where c.id = :id")
                .setLong("id", id).uniqueResult();
    }

    @Override
    public Long getOperationFrequency(Long id) {
        return (Long) getSession().createQuery("SELECT s.id FROM Client c JOIN c.operationFrequency s where c.id = :id")
                .setLong("id", id).uniqueResult();
    }

    @Override
    public Long getMarketExperience(Long id) {
        return (Long) getSession().createQuery("SELECT s.id FROM Client c JOIN c.marketExperience s where c.id = :id")
                .setLong("id", id).uniqueResult();
    }

    @Override
    public List<BasicClient> findBasicClientsBy(String name) {
        return (List<BasicClient>) getSession().createQuery("SELECT c.id as id, c.names as names, c.lastNames as lastNames, c.businessName as businessName, c.individual as individual FROM Client c where c.names like :name or c.lastNames like :name or c.businessName like :name")
                .setString("name", "%" + name + "%")
                .setResultTransformer(Transformers.aliasToBean(BasicClient.class))
                .list();
    }

    @Override
    public List<BasicClient> findBasicClientsForGeneralFileBy(String name) {
        return (List<BasicClient>) getSession().createQuery(
                "SELECT c.id as id, c.names as names, c.lastNames as lastNames, c.businessName as businessName, c.individual as individual " +
                        "FROM Client c " +
                        "where (c.names like :name or c.lastNames like :name or c.businessName like :name)" +
                        "and c.id not in (select f.client.id from GeneralFile f)")
                .setString("name", "%" + name + "%")
                .setResultTransformer(Transformers.aliasToBean(BasicClient.class))
                .list();
    }

    @Override
    public List<BasicClient> findBasicClientsForFileMovementBy(String name) {
        return (List<BasicClient>) getSession().createQuery(
                "SELECT c.id as id, c.names as names, c.lastNames as lastNames, c.businessName as businessName, c.individual as individual " +
                        "FROM Client c " +
                        "where (c.names like :name or c.lastNames like :name or c.businessName like :name) " +
                        "and c.id not in (select f.client.id from FileMovement f where f.returnDate is null) " +
                        "and c.id in (select g.client.id from GeneralFile g)")
                .setString("name", "%" + name + "%")
                .setResultTransformer(Transformers.aliasToBean(BasicClient.class))
                .list();
    }

    @Override
    public List<ClientLevelView> findAllGridClients() {
        return (List<ClientLevelView>) getSession().createQuery(
                "FROM ClientLevelView")
                .list();
    }

    @Override
    public List<ClientLevelView> findGridClientsBy(String level) {
        return (List<ClientLevelView>) getSession().createQuery(
                "FROM ClientLevelView x WHERE x.level = :level")
                .setString("level", level)
                .list();
    }

    @Override
    public List<Client> getByCuitCuil(String cuitCuil) {
        return (List<Client>) getSession().createQuery(
                "FROM Client c WHERE c.cuitCuil = :cuitCuil")
                .setString("cuitCuil", cuitCuil)
                .list();
    }

    @Override
    public BasicClient getBasicClientBy(Long id) {
        return (BasicClient) getSession().createQuery("SELECT c.id as id, c.names as names, c.lastNames as lastNames, c.businessName as businessName, c.individual as individual FROM Client c where c.id = :id")
                .setLong("id", id)
                .setResultTransformer(Transformers.aliasToBean(BasicClient.class))
                .uniqueResult();
    }

}
