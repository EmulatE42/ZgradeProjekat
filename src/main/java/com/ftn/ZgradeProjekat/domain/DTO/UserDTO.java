package com.ftn.ZgradeProjekat.domain.DTO;

import com.ftn.ZgradeProjekat.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by djuro on 11/30/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO
{
    private Integer id;
    private String username;

    public UserDTO(User user)
    {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
