package com.ftn.ZgradeProjekat.service;

import com.ftn.ZgradeProjekat.domain.Parliament;
import com.ftn.ZgradeProjekat.domain.Session;

import java.util.List;

/**
 * Created by Momir on 28.11.2017.
 */
public interface SessionService {

    Session addSession(Session session);
    Session getSession(Long id);
    boolean deleteSession(Long id);
    List<Session> getSessions();
}
