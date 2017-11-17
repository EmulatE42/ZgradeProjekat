package com.ftn.ZgradeProjekat.service;

import com.ftn.ZgradeProjekat.domain.Parlament;

/**
 * Created by Momir on 17.11.2017.
 */
public interface ParlamentService {

    Parlament addParlament(Parlament parlament);
    Parlament getParlament(Long id);
    boolean deleteParlament(Long id);


}
