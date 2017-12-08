package com.ftn.ZgradeProjekat.repository;

import com.ftn.ZgradeProjekat.domain.User;
import com.ftn.ZgradeProjekat.domain.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by djuro on 11/29/2017.
 */
public interface UserAuthorityRepository extends JpaRepository<UserAuthority,Long> {
}
