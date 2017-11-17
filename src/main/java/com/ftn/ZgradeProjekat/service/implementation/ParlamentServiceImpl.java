package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.Parlament;
import com.ftn.ZgradeProjekat.repository.ParlamentRepository;
import com.ftn.ZgradeProjekat.service.ParlamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Momir on 17.11.2017.
 */
@Service
public class ParlamentServiceImpl implements ParlamentService{

    @Autowired
    private ParlamentRepository parlamentRepository;

    @Override
    public Parlament addParlament(Parlament parlament) {

        Parlament saved = this.parlamentRepository.save(parlament);
        return saved;
    }

    @Override
    public Parlament getParlament(Long id) {

        Parlament parlament = this.parlamentRepository.findById(id);
        return parlament;
    }

    @Override
    public boolean deleteParlament(Long id) {

        Parlament parlament = this.parlamentRepository.findById(id);

        if(parlament != null)
        {
            this.parlamentRepository.delete(parlament);
            return true;
        }
        return false;
    }

}
