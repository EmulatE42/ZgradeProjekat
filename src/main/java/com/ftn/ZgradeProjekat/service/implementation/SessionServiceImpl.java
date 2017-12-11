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

/**
 * Implementation service for managing Session.
 */

@Service
public class SessionServiceImpl implements SessionService{

    @Autowired
    private SessionRepository sessionRepository;

    /**
     * Save a session.
     *
     * @param session the entity to add to database
     * @return the persisted entity
     */
    @Override
    public Session addSession(Session session) {

        Session saved = this.sessionRepository.save(session);
        return saved;
    }

    /**
     * Return one a session by id.
     *
     * @param id -
     * @return session
     */
    @Override
    public Session getSession(Long id) {

        Session session = this.sessionRepository.findById(id);
        return session;
    }

    /**
     * Delete one a session by id.
     *
     * @param id -
     * @return true if session is being deleted or false
     */
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

    /**
     * Return list of sessions.
     *
     * @return sessions
     */
    @Override
    public List<Session> getSessions() {

        List<Session> sessions = this.sessionRepository.findAll();
        return sessions;
    }
}
