package com.ftn.ZgradeProjekat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Momir on 14.11.2017.
 */

@Entity
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Getter
@Setter
@Table(name = "session")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "session_date")
    private Date date;

    @OneToMany(cascade=CascadeType.ALL)
    private Set<Topic> topics;

    @Column(name = "session_record")
    private String record;

    @Column(name = "session_timetable")
    private String timetable;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "session_user_id")
    private User creator;

    @ManyToOne(cascade=CascadeType.MERGE)
    private Parliament parliament;
}
