package com.ftn.ZgradeProjekat.domain.DTO;

import com.ftn.ZgradeProjekat.domain.Bug;
import com.ftn.ZgradeProjekat.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by djuro on 11/29/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BugDTO
{

    private Long id;
    private String description;
    private Date dateOfBug;
    private Set<CommentDTO> comments;
    private Boolean finished;
    private ResponsiblePersonDTO responsiblePersonDTO;

    public BugDTO(Bug bug)
    {
        this.id = bug.getId();
        this.description = bug.getDescription();
        this.dateOfBug = bug.getDateOfBug();
        this.finished = bug.getFinished();
        this.comments = new HashSet<>();
        for(Comment comment : bug.getComments())
        {
            this.comments.add(new CommentDTO(comment));
        }
        this.responsiblePersonDTO = new ResponsiblePersonDTO(bug.getResponsiblePerson());
    }
}
