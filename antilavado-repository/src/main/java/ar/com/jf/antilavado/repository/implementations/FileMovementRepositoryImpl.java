package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.generic.AbstractGenericRepository;
import ar.com.jf.antilavado.repository.interfaces.FileMovementRepository;
import ar.com.jf.antilavado.repository.model.FileMovement;
import org.springframework.stereotype.Repository;

/**
 * FileMovementRepositoryImpl.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 26/07/2015.
 */
@Repository
public class FileMovementRepositoryImpl extends AbstractGenericRepository<Long, FileMovement> implements FileMovementRepository {

    @Override
    public Class getDomainClass() {
        return FileMovement.class;
    }

}
