package com.ftn.ZgradeProjekat.domain.DTO;


import com.ftn.ZgradeProjekat.domain.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by EmulatE on 10-Dec-17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
