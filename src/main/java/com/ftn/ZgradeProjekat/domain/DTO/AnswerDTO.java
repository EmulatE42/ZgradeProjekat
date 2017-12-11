package com.ftn.ZgradeProjekat.domain.DTO;


import com.ftn.ZgradeProjekat.domain.Answer;
import lombok.*;

/**
 * Created by EmulatE on 10-Dec-17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude="questionDTO")
public class AnswerDTO {


    private Long id;
    private String text; // ako jep prvi
    private int rate; // ako je drugi
    private String choiced; // ako je treci tip


    public AnswerDTO(Answer a)
    {
        this.id = a.getId();
        this.text = a.getText();
        this.rate = a.getRate();
        this.choiced = a.getChoiced();
    }


}
