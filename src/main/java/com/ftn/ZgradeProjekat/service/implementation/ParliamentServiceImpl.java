package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.Parliament;
import com.ftn.ZgradeProjekat.repository.ParliamentRepository;
import com.ftn.ZgradeProjekat.service.ParliamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Momir on 17.11.2017.
 */
@Service
public class ParliamentServiceImpl implements ParliamentService {

    @Autowired
    private ParliamentRepository parliamentRepository;

    @Override
    public Parliament addParliament(Parliament parliament) {

        Parliament saved = this.parliamentRepository.save(parliament);
        return saved;
    }

    @Override
    public Parliament getParliament(Long id) {

        Parliament parliament = this.parliamentRepository.findById(id);
        return parliament;
    }

    @Override
    public boolean deleteParliament(Long id) {

        Parliament parliament = this.parliamentRepository.findById(id);

        if(parliament != null)
        {
            this.parliamentRepository.delete(parliament);
            return true;
        }
        return false;
    }

}
