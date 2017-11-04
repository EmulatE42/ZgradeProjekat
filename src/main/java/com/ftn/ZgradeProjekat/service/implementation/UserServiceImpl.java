package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.User;
import com.ftn.ZgradeProjekat.repository.UserRepository;
import com.ftn.ZgradeProjekat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by EmulatE on 02-Nov-17.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
        this.userRepository.save(user);
        return user;
    }
}
