package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.generic.AbstractGenericRepository;
import ar.com.jf.antilavado.repository.interfaces.GeneralFileRepository;
import ar.com.jf.antilavado.repository.model.Client;
import ar.com.jf.antilavado.repository.model.GeneralFile;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * GeneralFileRepositoryImpl.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 26/07/2015.
 */
@Repository
public class GeneralFileRepositoryImpl extends AbstractGenericRepository<Long, GeneralFile> implements GeneralFileRepository {

    @Override
    public Class getDomainClass() {
        return GeneralFile.class;
    }

    @Override
    public boolean isTheClientHasAGeneralFile(Long clientId){
        Query query = getSession().createQuery(
                "select count(*) from GeneralFile f where f.client.id=:clientId");
        query.setLong("clientId", clientId);
        if((Long)query.uniqueResult() > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public GeneralFile getBy(Long clientId) {
        return (GeneralFile) getSession().createQuery(
                "FROM GeneralFile g WHERE g.client.id = :clientId")
                .setLong("clientId", clientId)
                .uniqueResult();
    }

}
