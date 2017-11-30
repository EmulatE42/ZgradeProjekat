package com.ftn.ZgradeProjekat.controller;

import com.ftn.ZgradeProjekat.domain.DTO.*;
import com.ftn.ZgradeProjekat.domain.Institution;
import com.ftn.ZgradeProjekat.domain.User;
import com.ftn.ZgradeProjekat.security.TokenUtils;
import com.ftn.ZgradeProjekat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by djuro on 11/4/2017.
 */
@RestController
@CrossOrigin
public class UserController
{
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    TokenUtils tokenUtils;

    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginDTO) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    loginDTO.getUsername(), loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());
            String userToken = tokenUtils.generateToken(details);
            User user = this.userService.getUserByUsername(loginDTO.getUsername());
            return new ResponseEntity<>(new LoginResponseDTO(userToken, user.getId(),loginDTO.getUsername(),user.getUserAuthorities().iterator().next().getAuthority().getName()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Invalid login", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/api/registerUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody RegisterUserDTO registerUser)
    {
        LoginResponseDTO responseDTO = this.userService.registerUser(registerUser);
        if(responseDTO == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(registerUser, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user/getAllTenants", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TenantDTO>> getAllTenants()
    {
        List<TenantDTO> tenantDTOs = this.userService.getAllTenants();
        if(tenantDTOs == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(tenantDTOs, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/getAllInstitution", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<InstitutionDTO>> getAllInstitutions()
    {
        List<InstitutionDTO> institutionDTOs = this.userService.getAllInstitution();
        if(institutionDTOs == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(institutionDTOs, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/getAllTenantsFromBuilding/{buildingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TenantDTO>> getAllTenantsFromBuilding(@PathVariable("buildingId") Long buildingId)
    {
        List<TenantDTO> tenantDTOs = this.userService.getAllTenantsFromBuilding(buildingId);
        if(tenantDTOs == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(tenantDTOs, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/getApartmentsOfTenant/{tenantId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LocationDTO>> getApartmentsOfTenant(@PathVariable("tenantId") Integer tenantId)
    {
        List<LocationDTO> locationDTOs = this.userService.getApartmentsOfTenant(tenantId);
        if(locationDTOs == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(locationDTOs, HttpStatus.OK);
    }
}
