package com.ftn.ZgradeProjekat.repository;

import com.ftn.ZgradeProjekat.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by EmulatE on 02-Nov-17.
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("SELECT w FROM User w WHERE w.username = :username")
    User findByUsername(@Param("username") String username);
}
