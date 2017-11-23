package com.ftn.ZgradeProjekat.repository;

import com.ftn.ZgradeProjekat.domain.Parliament;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Momir on 17.11.2017.
 */
public interface ParliamentRepository extends JpaRepository<Parliament, Integer> {

    Parliament findById(Long id);

}
