package com.ftn.ZgradeProjekat.service;

import com.ftn.ZgradeProjekat.domain.DTO.LoginRequestDTO;
import com.ftn.ZgradeProjekat.domain.DTO.LoginResponseDTO;
import com.ftn.ZgradeProjekat.domain.DTO.RegisterUserDTO;
import com.ftn.ZgradeProjekat.domain.DTO.TenantDTO;
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
}
