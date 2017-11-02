package com.ftn.ZgradeProjekat.controller;

import com.ftn.ZgradeProjekat.domain.Korisnik;
import com.ftn.ZgradeProjekat.security.TokenUtils;
import com.ftn.ZgradeProjekat.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by EmulatE on 02-Nov-17.
 */
@RestController
public class KorisnikController {

    @Autowired
    KorisnikService korisnikService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    TokenUtils tokenUtils;

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @CrossOrigin
    @RequestMapping(value = "/api/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> dajKorisnika() {


        Korisnik k = new Korisnik("admin", "$2a$04$SwzgBrIJZhfnzOw7KFcdzOTiY6EFVwIpG7fkF/D1w26G1.fWsi.aK");
        System.out.println("OVO JE " + k.getUsername());
        //Korisnik k1 = korisnikService.save(k);

        //try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    k.getUsername(), k.getPassword());
            System.out.println(token.toString());
            Authentication authentication = authenticationManager.authenticate(token);
            //System.out.println("Usaoooooooooooooooooo2");
            SecurityContextHolder.getContext().setAuthentication(authentication);
          //  System.out.println("Usaoooooooooooooooooo3");
            UserDetails details = userDetailsService.loadUserByUsername(k.getUsername());
            //System.out.println("Usaoooooooooooooooooo4");
            return new ResponseEntity<String>(tokenUtils.generateToken(details), HttpStatus.OK);
       // } catch (Exception ex) {
           // System.out.println(ex);
            //return new ResponseEntity<String>("Invalid login", HttpStatus.BAD_REQUEST);
      //  }

        //return new ResponseEntity(k1 != null ? k1 : "{}", HttpStatus.OK);
    }

}
