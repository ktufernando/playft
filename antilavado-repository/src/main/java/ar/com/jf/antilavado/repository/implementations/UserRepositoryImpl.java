package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.dto.response.user.BasicUser;
import ar.com.jf.antilavado.repository.generic.AbstractGenericRepository;
import ar.com.jf.antilavado.repository.interfaces.UserRepository;
import ar.com.jf.antilavado.repository.model.User;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserRepositoryImpl.java
 * <p>
 * PLAYFT.
 * <p>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p>
 * Created by fvaldes on 26/07/2015.
 */
@Repository
public class UserRepositoryImpl extends AbstractGenericRepository<Long, User> implements UserRepository {

    @Override
    public Class getDomainClass() {
        return User.class;
    }

    public User findOneByLogin(String username) {
        return (User) getSession().createCriteria(getDomainClass())
                .add(Restrictions.eq("login", username))
                .uniqueResult();
    }

    @Override
    public Long activeUsers() {
        return (Long) getSession().createQuery("SELECT COUNT(u) FROM User u WHERE u.enabled = true").uniqueResult();
    }

    @Override
    public List<BasicUser> findAllBasicUsers() {
        return (List<BasicUser>) getSession().createQuery(
                "SELECT u.id as id, u.firstName as firstName, u.lastName as lastName " +
                        "FROM User u")
                .setResultTransformer(Transformers.aliasToBean(BasicUser.class))
                .list();
    }

}
