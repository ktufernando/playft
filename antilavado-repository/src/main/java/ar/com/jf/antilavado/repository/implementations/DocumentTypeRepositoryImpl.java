package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.generic.AbstractGenericRepository;
import ar.com.jf.antilavado.repository.interfaces.DocumentTypeRepository;
import ar.com.jf.antilavado.repository.model.DocumentType;
import org.springframework.stereotype.Repository;

/**
 * DocumentTypeRepositoryImpl.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 26/07/2015.
 */
@Repository
public class DocumentTypeRepositoryImpl extends AbstractGenericRepository<Long, DocumentType> implements DocumentTypeRepository {

    @Override
    public Class getDomainClass() {
        return DocumentType.class;
    }

}
