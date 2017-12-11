package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.Parliament;
import com.ftn.ZgradeProjekat.repository.ParliamentRepository;
import com.ftn.ZgradeProjekat.service.ParliamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by Momir on 17.11.2017.
 */

/**
 * Implementation service for managing Parliament.
 */

@Service
public class ParliamentServiceImpl implements ParliamentService {

    @Autowired
    private ParliamentRepository parliamentRepository;

    /**
     * Save a parliament.
     *
     * @param parliament the entity to add to database
     * @return the persisted entity
     */
    @Override
    public Parliament addParliament(Parliament parliament) {

        Parliament saved = this.parliamentRepository.save(parliament);
        return saved;
    }

    /**
     * Return one a parliament by id.
     *
     * @param id -
     * @return parliament
     */
    @Override
    public Parliament getParliament(Long id) {

        Parliament parliament = this.parliamentRepository.findById(id);
        return parliament;
    }

    /**
     * Delete one a parliament by id.
     *
     * @param id -
     * @return true if parliament is being deleted or false
     */
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

    /**
     * Return list of parliaments.
     *
     * @return parliaments
     */
    @Override
    public List<Parliament> getParliaments() {

        return this.parliamentRepository.findAll();
    }

}
