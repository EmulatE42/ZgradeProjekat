package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.*;
import com.ftn.ZgradeProjekat.domain.DTO.*;
import com.ftn.ZgradeProjekat.repository.*;
import com.ftn.ZgradeProjekat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @Autowired
    InstitutionRepository institutionRepository;

    @Autowired
    UserAuthorityRepository userAuthorityRepository;

    @Autowired
    FirmRepository firmRepository;

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
        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setAuthority(authority);
        this.userAuthorityRepository.save(userAuthority);
        switch (registerUser.getRole()) {
            case "ROLE_ADMIN":
            {
                Admin admin = new Admin(registerUser);
                admin.addUserAuthority(userAuthority);
                this.adminRepository.save(admin);
                userAuthority.setUser(admin);
                this.userAuthorityRepository.save(userAuthority);
                user.setId(admin.getId());
                user.addUserAuthority(userAuthority);
                break;
            }
            case "TENANT":
            {
                Tenant tenant = new Tenant(registerUser);
                tenant.addUserAuthority(userAuthority);
                this.tenantRepository.save(tenant);
                userAuthority.setUser(tenant);
                this.userAuthorityRepository.save(userAuthority);
                user.setId(tenant.getId());
                user.addUserAuthority(userAuthority);
                break;
            }
            case "INSTITUTION":
            {
                Institution institution = new Institution(registerUser);
                institution.addUserAuthority(userAuthority);
                this.institutionRepository.save(institution);
                userAuthority.setUser(institution);
                this.userAuthorityRepository.save(userAuthority);
                user.setId(institution.getId());
                user.addUserAuthority(userAuthority);
                break;
            }
            case "FIRM":
            {
                Firm firm = new Firm(registerUser);
                firm.addUserAuthority(userAuthority);
                this.firmRepository.save(firm);
                userAuthority.setUser(firm);
                this.userAuthorityRepository.save(userAuthority);
                user.setId(firm.getId());
                user.addUserAuthority(userAuthority);
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
        User usr = userRepository.findByUsername(user.getUsername());
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

    @Override
    public List<InstitutionDTO> getAllInstitution()
    {
        List<Institution> institutions = this.institutionRepository.findAll();
        List<InstitutionDTO> institutionDTOs = new ArrayList<>();
        for(Institution institution : institutions)
        {
            institutionDTOs.add(new InstitutionDTO(institution));
        }
        return institutionDTOs;
    }

    @Override
    public List<TenantDTO> getAllTenantsFromBuilding(Long buildingId)
    {
        List<Tenant> tenants = tenantRepository.findAll();
        List<TenantDTO> tenantDTOs = new ArrayList<>();
        for(Tenant tenant : tenants)
        {
            for(Apartment apartment: tenant.getApartments())
            {
                if(apartment.getBuilding().getId()==buildingId)
                {
                    tenantDTOs.add(new TenantDTO(tenant));
                    break;
                }
            }

        }
        return tenantDTOs;
    }

    @Override
    public List<LocationDTO> getApartmentsOfTenant(Integer tenantId)
    {
        Tenant tenant = this.tenantRepository.findById(tenantId);
        Set<Apartment> apartments = tenant.getApartments();
        List<LocationDTO> locationDTOs = new ArrayList<>();
        for(Apartment apartment : apartments)
        {
            locationDTOs.add(new LocationDTO(apartment));
        }
        return locationDTOs;
    }

    @Override
    public User getUserByUsername(String username)
    {
        User user = null;
        user = this.userRepository.findByUsername(username);
        return user;
    }

    @Override
    public List<FirmDTO> getAllFirms()
    {
        List<Firm> firms = this.firmRepository.findAll();
        List<FirmDTO> firmDTOs = null;
        if(firms != null)
        {
            firmDTOs = new ArrayList<>();
            for(Firm firm : firms)
            {
                firmDTOs.add(new FirmDTO(firm));
            }
        }
        return firmDTOs;
    }
}
