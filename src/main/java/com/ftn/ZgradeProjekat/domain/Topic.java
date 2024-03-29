package com.ftn.ZgradeProjekat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ftn.ZgradeProjekat.domain.DTO.TopicDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by djuro on 11/4/2017.
 */
@Entity
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Getter
@Setter
@Table(name = "topic")
public class Topic
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "topic_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "topic_description")
    private String description;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "topic_user_id")
    private User creator;

    @Column(name = "topic_accepted")
    private Boolean accepted;

    @Column(name = "pos_votes")
    private Long pos_votes;

    @Column(name = "neg_votes")
    private Long neg_votes;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JsonIgnore
    private Session session;

    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Set<Vote> votes = new HashSet<Vote>();

    public Topic(TopicDTO topicDTO, Session session)
    {
        this.id = topicDTO.getId();
        this.description = topicDTO.getDescription();
        this.creator = topicDTO.getCreator();
        this.accepted = topicDTO.getAccepted();
        this.pos_votes = topicDTO.getPos_votes();
        this.neg_votes = topicDTO.getNeg_votes();
        this.session = session;
    }
}
