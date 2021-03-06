package ar.com.jf.antilavado.repository.interfaces;

import ar.com.jf.antilavado.repository.generic.GenericRepository;
import ar.com.jf.antilavado.repository.model.GeneralFile;

/**
 * GeneralFileRepository.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 14/09/2015.
 */
public interface GeneralFileRepository extends GenericRepository<Long, GeneralFile> {

    boolean isTheClientHasAGeneralFile(Long clientId);

    GeneralFile getBy(Long clientId);

}
