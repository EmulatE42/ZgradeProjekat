package com.ftn.ZgradeProjekat.service;

import com.ftn.ZgradeProjekat.domain.Parliament;

/**
 * Created by Momir on 17.11.2017.
 */
public interface ParliamentService {

    Parliament addParliament(Parliament parliament);
    Parliament getParliament(Long id);
    boolean deleteParliament(Long id);


}
