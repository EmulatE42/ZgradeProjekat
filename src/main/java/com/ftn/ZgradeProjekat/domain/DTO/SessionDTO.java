package com.ftn.ZgradeProjekat.domain.DTO;

import com.ftn.ZgradeProjekat.domain.Parliament;
import com.ftn.ZgradeProjekat.domain.Session;
import com.ftn.ZgradeProjekat.domain.Topic;
import com.ftn.ZgradeProjekat.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

/**
 * Created by Momir on 01.12.2017.
 */

@Getter
@Setter
public class SessionDTO {

    private Long id;
    private Date date;
    private Set<Topic> topics;
    private String record;
    private String timetable;
    private User creator;
    private Parliament parliament;

    public SessionDTO() {}

    public SessionDTO(Session session)
    {
        this.id = session.getId();
        this.date = session.getDate();
        this.topics = session.getTopics();
        this.record = session.getRecord();
        this.timetable = session.getTimetable();
        this.creator = session.getCreator();
        this.parliament = session.getParliament();
    }
}
