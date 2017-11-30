package com.ftn.ZgradeProjekat.domain;

import com.ftn.ZgradeProjekat.domain.DTO.BugDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
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
@Table(name = "bug")
public class Bug
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bug_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "bug_description")
    private String description;

    @Column(name = "bug_date_of_bug")
    private Date dateOfBug;

    @OneToMany
    private Set<Comment> comments;

    @Column(name = "bug_finished")
    private Boolean finished;

    @ManyToOne
    private ResponsiblePerson responsiblePerson;

    public Bug(BugDTO bugDTO)
    {
        this.description = bugDTO.getDescription();
        this.dateOfBug = bugDTO.getDateOfBug();
        this.comments = new HashSet<>();
        this.finished = false;
    }

    public void addComment(Comment comment)
    {
        this.comments.add(comment);
    }

    public void removeComment(Comment comment)
    {
        this.comments.remove(comment);
    }
}
