package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.*;
import com.ftn.ZgradeProjekat.domain.DTO.LoginRequestDTO;
import com.ftn.ZgradeProjekat.domain.DTO.LoginResponseDTO;
import com.ftn.ZgradeProjekat.domain.DTO.RegisterUserDTO;
import com.ftn.ZgradeProjekat.domain.DTO.TenantDTO;
import com.ftn.ZgradeProjekat.repository.*;
import com.ftn.ZgradeProjekat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    TenantRepository tenantRepository;

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
        Authority authority = this.authorityRepository.findByName(registerUser.getRole());  //u bazi se nalaze predefinisane uloge
        User user = new User(registerUser.getUsername(),registerUser.getPassword());
        //UserAuthority userAuthority = new UserAuthority(user, authority);
        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setAuthority(authority);
        switch (registerUser.getRole()) {
            case "ROLE_ADMIN":
            {
                Admin admin = new Admin(user);
                userAuthority.setUser(admin);
                admin.addUserAuthority(userAuthority);
                this.adminRepository.save(admin);
                user.setId(admin.getId());
                break;
            }
            case "RESPONSIBLE_PERSON":
            {
                ResponsiblePerson responsiblePerson = new ResponsiblePerson(user);
                userAuthority.setUser(responsiblePerson);
                responsiblePerson.addUserAuthority(userAuthority);
                this.responsiblePersonRepository.save(responsiblePerson);
                user.setId(responsiblePerson.getId());
                break;
            }
            case "TENANT":
            {
                System.out.println("/////////////");
                Tenant tenant = new Tenant(user);
                tenant.setFirstname(registerUser.getFirstname());
                tenant.setLastname(registerUser.getLastname());
                userAuthority.setUser(tenant);
                tenant.addUserAuthority(userAuthority);
                this.tenantRepository.save(tenant);
                System.out.println("id /////////////"+tenant.getId());
                user.setId(tenant.getId());
                break;
            }
            default:
                break;
        }
        System.out.println("ROLEEE   "+registerUser.getRole());
        System.out.println("USERNAME   "+registerUser.getUsername());
        System.out.println("PASWORD   "+registerUser.getPassword());
        System.out.println("id   "+user.getId());
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

    @Override
    public List<TenantDTO> getAllTenants()
    {
        List<Tenant> tenants = tenantRepository.findAll();
        List<TenantDTO> tenantDTOs = new ArrayList<>();
        for(Tenant tenant : tenants)
        {
            tenantDTOs.add(new TenantDTO(tenant));
        }
        return tenantDTOs;
    }
}
