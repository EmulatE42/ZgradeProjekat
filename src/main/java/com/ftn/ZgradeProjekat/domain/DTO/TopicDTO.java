package com.ftn.ZgradeProjekat.domain.DTO;

import com.ftn.ZgradeProjekat.domain.Session;
import com.ftn.ZgradeProjekat.domain.Topic;
import com.ftn.ZgradeProjekat.domain.User;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Momir on 03.12.2017.
 */
@Getter
@Setter
public class TopicDTO {

    private Long id;
    private String description;
    private User creator;
    private Boolean accepted;
    private Long votes;

    public TopicDTO() {}

    public TopicDTO(Topic topic)
    {
        this.id = topic.getId();
        this.description = topic.getDescription();
        this.creator = topic.getCreator();
        this.accepted = topic.getAccepted();
        this.votes = topic.getVotes();
    }
}
