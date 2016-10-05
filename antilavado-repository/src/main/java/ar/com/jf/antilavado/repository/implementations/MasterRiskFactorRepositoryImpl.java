package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.dto.response.CodDesc;
import ar.com.jf.antilavado.repository.generic.AbstractGenericRepository;
import ar.com.jf.antilavado.repository.interfaces.FactorLevelRepository;
import ar.com.jf.antilavado.repository.interfaces.MasterRiskFactorRepository;
import ar.com.jf.antilavado.repository.model.FactorLevel;
import ar.com.jf.antilavado.repository.model.MasterRiskFactor;
import com.google.common.collect.Lists;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.persister.walking.spi.AttributeDefinition;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * MasterRiskFactorRepositoryImpl.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 17/11/2015.
 */
@Repository
public class MasterRiskFactorRepositoryImpl extends AbstractGenericRepository<Long, MasterRiskFactor> implements MasterRiskFactorRepository {

    @Override
    public Class getDomainClass() {
        return MasterRiskFactor.class;
    }

    public List<CodDesc> tableNames(){
        List<CodDesc> tableNames = Lists.newArrayList();
        Map<String, ClassMetadata> classMetaDataMap = getSession().getSessionFactory().getAllClassMetadata();
        for(Map.Entry<String, ClassMetadata> metaDataMap : classMetaDataMap.entrySet()) {
            ClassMetadata classMetadata = metaDataMap.getValue();
            AbstractEntityPersister abstractEntityPersister = (AbstractEntityPersister) classMetadata;
            String entityName = abstractEntityPersister.getEntityName();
            entityName = entityName.substring(entityName.lastIndexOf(".")+1);
            tableNames.add(new CodDesc(entityName, entityName));
        }
        return tableNames;
    }

    @Override
    public List<CodDesc> columnNames(String tableName) throws Exception{
        ClassMetadata classMetadata = getSession().getSessionFactory().getClassMetadata("ar.com.jf.antilavado.repository.model." + tableName);
        AbstractEntityPersister abstractEntityPersister = (AbstractEntityPersister) classMetadata;
        List<CodDesc> columnNames = Lists.newArrayList();
        for (AttributeDefinition att : abstractEntityPersister.getAttributes()){
            if(!att.getType().isAssociationType()){
                columnNames.add(new CodDesc(att.getName(), att.getName()));
            }
        }

        return columnNames;
    }

    @Override
    public String clientValue(String tableName, String columnName, Long clientId) {
        StringBuilder sql = new StringBuilder("SELECT o.");
        sql.append(columnName);
        sql.append(" FROM ");
        sql.append(tableName);
        sql.append(" o where o.");
        if (!tableName.equalsIgnoreCase("client")){
            sql.append("client.");
        }
        sql.append("id = :clientId");
        Object result = getSession().createQuery(sql.toString()).setLong("clientId", clientId).uniqueResult();
        return null;
    }

}
