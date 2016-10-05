package ar.com.jf.antilavado.service.user;

import ar.com.jf.antilavado.repository.dto.response.license.LicenseData;
import ar.com.jf.antilavado.repository.dto.response.user.BasicUser;
import ar.com.jf.antilavado.repository.model.User;
import ar.com.jf.antilavado.repository.interfaces.UserRepository;
import ar.com.jf.antilavado.service.exceptions.ServiceException;
import ar.com.jf.antilavado.service.license.LicenseService;
import com.google.common.base.Strings;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * UserServiceImpl.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LicenseService licenseService;

    @Transactional(readOnly = true)
    public User findOneByLogin(String login) {
        return userRepository.findOneByLogin(login);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User addUser(User user) throws Exception{

        LicenseData license = licenseService.getLicenseData();
        int activeUsers = userRepository.activeUsers().intValue();
        if (license.getUserLimit() <= activeUsers){
            throw new ServiceException("license.users.limit.add", "User limit validation");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setFirstName(WordUtils.capitalizeFully(user.getFirstName()));
        user.setLastName(WordUtils.capitalizeFully(user.getLastName()));
        userRepository.auditSave(user);
        userRepository.save(user);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(Long id) {
        return userRepository.get(id);
    }

    @Override
    @Transactional
    public void update(User user) throws Exception{

        User userModel = userRepository.get(user.getId());

        if(!userModel.isEnabled() && user.isEnabled()){
            LicenseData license = licenseService.getLicenseData();
            int activeUsers = userRepository.activeUsers().intValue();
            if (license.getUserLimit() <= activeUsers){
                throw new ServiceException("license.users.limit.enable", "User limit validation");
            }
        }


        if(!Strings.isNullOrEmpty(user.getPassword())){
            userModel.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userModel.setFirstName(WordUtils.capitalizeFully(user.getFirstName()));
        userModel.setLastName(WordUtils.capitalizeFully(user.getLastName()));
        userModel.setEmail(user.getEmail());
        userModel.setEnabled(user.isEnabled());
        userModel.setUserAuthorities(user.getUserAuthorities());

        userRepository.auditUpdate(userModel);
        userRepository.update(userModel);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.delete(userRepository.load(id));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean usernameValidation(String login) {
        User user = userRepository.findOneByLogin(login);
        if(user != null){
            return false;
        }
        return true;
    }

    @Transactional(readOnly = true)
    public List<BasicUser> findAllBasicUsers() {
        return userRepository.findAllBasicUsers();
    }

}
