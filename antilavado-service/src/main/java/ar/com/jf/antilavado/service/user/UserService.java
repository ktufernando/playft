package ar.com.jf.antilavado.service.user;

import ar.com.jf.antilavado.repository.dto.response.user.BasicUser;
import ar.com.jf.antilavado.repository.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * UserService.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */
public interface UserService {

    User findOneByLogin(String login);

    Collection<User> getAllUsers();

    User addUser(User user) throws Exception;

    User getUser(Long id);

    void update(User user) throws Exception;

    void delete(Long id);

    boolean usernameValidation(String login);

    @Transactional(readOnly = true)
    List<BasicUser> findAllBasicUsers();
}
