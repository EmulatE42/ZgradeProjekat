package com.ftn.ZgradeProjekat.repository;

import com.ftn.ZgradeProjekat.domain.Parlament;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Momir on 17.11.2017.
 */
public interface ParlamentRepository extends JpaRepository<Parlament, Integer> {

    Parlament findById(Long id);

}
