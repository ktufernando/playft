package ar.com.jf.antilavado.rest.endpoint;

import ar.com.jf.antilavado.repository.dto.response.BasicResponse;
import ar.com.jf.antilavado.repository.dto.response.user.BasicUser;
import ar.com.jf.antilavado.repository.model.User;
import ar.com.jf.antilavado.rest.annotation.PerformanceLog;
import ar.com.jf.antilavado.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

/**
 * UsersRest.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 30/10/2015.
 */
@RestController
public class UsersRest {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public User currentUser(Principal principal) {
        return userService.findOneByLogin(principal.getName());
    }

    @PerformanceLog
    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public
    @ResponseBody
    Collection<User> getAll() {
        return this.userService.getAllUsers();
    }

    @PerformanceLog
    @RequestMapping(value = "/admin/users", method = RequestMethod.POST)
    public
    @ResponseBody
    User add(@RequestBody User user) throws Exception{
        return this.userService.addUser(user);
    }

    @PerformanceLog
    @RequestMapping(value = "/admin/users/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User get(@PathVariable Long id) {
        return this.userService.getUser(id);
    }

    @PerformanceLog
    @RequestMapping(value = "/admin/users", method = RequestMethod.PUT)
    @ResponseBody
    public void update(@RequestBody User user) throws Exception {
        this.userService.update(user);
    }

    @PerformanceLog
    @RequestMapping(value = "/admin/users/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteProgram(@PathVariable Long id) {
        this.userService.delete(id);
    }

    @PerformanceLog
    @RequestMapping(value = "/admin/users/username/{username}", method = RequestMethod.GET)
    @ResponseBody
    public BasicResponse usernameValidation(@PathVariable String username) {
        return new BasicResponse(this.userService.usernameValidation(username));
    }

    @PerformanceLog
    @RequestMapping(value = "/user/users", method = RequestMethod.GET)
    public
    @ResponseBody
    Collection<BasicUser> findAllBasicUsers() {
        return this.userService.findAllBasicUsers();
    }
}
