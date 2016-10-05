package ar.com.jf.antilavado.repository.generic;

import ar.com.jf.antilavado.repository.model.AbstractAuditingEntity;

import java.io.Serializable;
import java.util.List;

/**
 * GenericRepository.java
 * <p>
 * PLAYFT.
 * <p>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p>
 * Created by fvaldes on 16/09/2015.
 */
public interface GenericRepository<ID extends Serializable, T extends Serializable> {

    T save(T t);

    void saveOrUpdate(T t);

    List<T> findAll();

    T load(ID id);

    T get(ID id);

    void delete(T t);

    void update(T t);

    T merge(T t);

    void auditSave(AbstractAuditingEntity e);

    void auditUpdate(AbstractAuditingEntity e);

}
