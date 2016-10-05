package ar.com.jf.antilavado.repository.generic;

import java.io.Serializable;
import java.util.List;

import ar.com.jf.antilavado.repository.model.AbstractAuditingEntity;
import ar.com.jf.antilavado.repository.util.UserAuditorUtils;
import com.google.common.base.Strings;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * AbstractGenericRepository.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 16/09/2015.
 */
public abstract class AbstractGenericRepository<ID extends Serializable, T extends Serializable>
		implements GenericRepository<ID, T> {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	private Class<T> clazz = getDomainClass();

	public abstract Class<T> getDomainClass();

	public T save(T t) {
		return (T)getSession().save(t);
	}

	public void saveOrUpdate(T t) {
		getSession().saveOrUpdate(t);
	}

	public List<T> findAll() {
		Criteria crit = getSession().createCriteria(getDomainClass());
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crit.list();
	}

	public T load(ID id) {
		return (T) getSession().load(getDomainClass(), id);
	}

	public T get(ID id) {
		return (T) getSession().get(getDomainClass(), id);
	}

	public void delete(T t) {
		getSession().delete(t);
	}

	public void update(T t) {
		getSession().update(t);
	}

	public T merge(T t){
		return (T)getSession().merge(t);
	}

	

	public void auditSave(AbstractAuditingEntity e) {
		DateTime date = DateTime.now();
		String user = UserAuditorUtils.getCurrentUser();
		user = Strings.isNullOrEmpty(user) ? "admin" : user;
		e.setCreatedBy(user);
		e.setCreatedDate(date);
	}

	public void auditUpdate(AbstractAuditingEntity e) {
		DateTime date = DateTime.now();
		String user = UserAuditorUtils.getCurrentUser();
		user = Strings.isNullOrEmpty(user) ? "admin" : user;
		e.setLastModifiedBy(user);
		e.setLastModifiedDate(date);
	}

}
