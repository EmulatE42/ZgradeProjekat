package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.*;
import com.ftn.ZgradeProjekat.domain.DTO.LoginRequestDTO;
import com.ftn.ZgradeProjekat.domain.DTO.LoginResponseDTO;
import com.ftn.ZgradeProjekat.domain.DTO.RegisterUserDTO;
import com.ftn.ZgradeProjekat.repository.AdminRepository;
import com.ftn.ZgradeProjekat.repository.AuthorityRepository;
import com.ftn.ZgradeProjekat.repository.ResponsiblePersonRepository;
import com.ftn.ZgradeProjekat.repository.UserRepository;
import com.ftn.ZgradeProjekat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by EmulatE on 02-Nov-17.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    ResponsiblePersonRepository responsiblePersonRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        this.userRepository.save(user);
        return user;
    }

    @Override
    public LoginResponseDTO registerUser(RegisterUserDTO registerUser)
    {
        registerUser.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        Authority authority = this.authorityRepository.findByName(registerUser.getRole());
        User user = new User(registerUser.getUsername(),registerUser.getPassword());
        //UserAuthority userAuthority = new UserAuthority(user, authority);
        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setUser(user);
        userAuthority.setAuthority(authority);
        user.addUserAuthority(userAuthority);
        switch (registerUser.getRole()) {
            case "ROLE_ADMIN":
            {
                Admin admin = new Admin(user);
                this.adminRepository.save(admin);
                user.setId(admin.getId());
                break;
            }
            case "RESPONSE_PERSON":
            {
                ResponsiblePerson responsiblePerson = new ResponsiblePerson(user);
                this.responsiblePersonRepository.save(responsiblePerson);
                user.setId(responsiblePerson.getId());
                break;
            }
            default:
                break;
        }
        return new LoginResponseDTO(user);
    }

    @Override
    public User getUserById(Integer userId)
    {
        User user = this.userRepository.getOne(userId);
        return user;
    }

    @Override
    public User changePassword(LoginRequestDTO user)
    {
        User usr = userRepository.findByIme(user.getUsername());
        usr.setPassword(user.getPassword());
        return usr;
    }
}
