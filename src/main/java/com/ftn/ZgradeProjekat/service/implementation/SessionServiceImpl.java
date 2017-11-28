package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.Parliament;
import com.ftn.ZgradeProjekat.domain.Session;
import com.ftn.ZgradeProjekat.repository.SessionRepository;
import com.ftn.ZgradeProjekat.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Momir on 28.11.2017.
 */
@Service
public class SessionServiceImpl implements SessionService{

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public Session addSession(Session session) {

        Session saved = this.sessionRepository.save(session);
        return saved;
    }

    @Override
    public Session getSession(Long id) {

        Session session = this.sessionRepository.findById(id);
        return session;
    }

    @Override
    public boolean deleteSession(Long id) {

        Session session = this.sessionRepository.findById(id);

        if(session != null)
        {
            this.sessionRepository.delete(session);
            return true;
        }
        return false;
    }

    @Override
    public List<Session> getSessions() {

        List<Session> sessions = this.sessionRepository.findAll();
        return sessions;
    }
}
