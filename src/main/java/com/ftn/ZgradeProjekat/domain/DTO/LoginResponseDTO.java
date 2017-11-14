package com.ftn.ZgradeProjekat.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by djuro on 11/14/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO
{
    private String token;
    private Long id;
    private String username;
    private String role;
}
