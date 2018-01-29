package com.ftn.ZgradeProjekat.service;

import com.ftn.ZgradeProjekat.domain.DTO.ParliamentDTO;
import com.ftn.ZgradeProjekat.domain.Parliament;

import java.util.List;
import java.util.Set;

/**
 * Created by Momir on 17.11.2017.
 */
public interface ParliamentService {

    Parliament addParliament(Parliament parliament);
    Parliament getParliament(Long id);
    boolean deleteParliament(Long id);
    List<Parliament> getParliaments();
}
