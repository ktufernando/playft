package ar.com.jf.antilavado.repository.interfaces;

import ar.com.jf.antilavado.repository.dto.response.user.BasicUser;
import ar.com.jf.antilavado.repository.generic.GenericRepository;
import ar.com.jf.antilavado.repository.model.User;

import java.util.List;

/**
 * UserRepository.java
 * <p>
 * PLAYFT.
 * <p>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p>
 * Created by fvaldes on 14/09/2015.
 */
public interface UserRepository extends GenericRepository<Long, User> {

    User findOneByLogin(String username);

    Long activeUsers();

    List<BasicUser> findAllBasicUsers();
}
