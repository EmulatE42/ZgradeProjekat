package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.Korisnik;
import com.ftn.ZgradeProjekat.repository.KorisnikRepository;
import com.ftn.ZgradeProjekat.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by EmulatE on 02-Nov-17.
 */
@Service
public class KorisnikServiceImpl implements KorisnikService {

    @Autowired
    KorisnikRepository korisnikRepository;

    @Override
    public Korisnik save(Korisnik korisnik) {
        this.korisnikRepository.save(korisnik);
        return korisnik;
    }
}
