package com.ftn.ZgradeProjekat.repository;

import com.ftn.ZgradeProjekat.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Momir on 28.11.2017.
 */
public interface SessionRepository extends JpaRepository<Session, Integer>{

    Session findById(Long id);
}
