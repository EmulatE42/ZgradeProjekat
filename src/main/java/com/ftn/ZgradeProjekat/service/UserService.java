package com.ftn.ZgradeProjekat.service;

import com.ftn.ZgradeProjekat.domain.DTO.*;
import com.ftn.ZgradeProjekat.domain.ResponsiblePerson;
import com.ftn.ZgradeProjekat.domain.Tenant;
import com.ftn.ZgradeProjekat.domain.User;

import java.util.List;

/**
 * Created by EmulatE on 02-Nov-17.
 */
public interface UserService {
    User save(User user);

    LoginResponseDTO registerUser(RegisterUserDTO registerUser);

    User getUserById(Integer userId);

    User changePassword(LoginRequestDTO user);

    List<TenantDTO> getAllTenants();

    List<InstitutionDTO> getAllInstitution();

    List<TenantDTO> getAllTenantsFromBuilding(Long buildingId);
}
