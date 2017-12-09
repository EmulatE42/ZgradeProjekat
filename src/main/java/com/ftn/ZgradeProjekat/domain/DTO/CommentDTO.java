package com.ftn.ZgradeProjekat.domain.DTO;

import com.ftn.ZgradeProjekat.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by djuro on 11/29/2017.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO
{
    private Long id;
    private String text;
    private UserDTO user;

    public CommentDTO(Comment comment)
    {
        this.id = comment.getId();
        this.text = comment.getText();
        this.user = new UserDTO(comment.getCreator());
    }
}
