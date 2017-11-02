package com.ftn.ZgradeProjekat.repository;

import com.ftn.ZgradeProjekat.domain.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by EmulatE on 02-Nov-17.
 */
public interface KorisnikRepository extends JpaRepository<Korisnik,Integer> {

    @Query("SELECT w FROM Korisnik w WHERE w.username = :username")
    Korisnik findByIme(@Param("username") String username);
}
