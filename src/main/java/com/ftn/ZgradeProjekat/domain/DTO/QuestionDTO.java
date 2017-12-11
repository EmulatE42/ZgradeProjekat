package com.ftn.ZgradeProjekat.domain.DTO;

import com.ftn.ZgradeProjekat.domain.Answer;
import com.ftn.ZgradeProjekat.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Set;

/**
 * Created by EmulatE on 10-Dec-17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {


    private Long id;
    private String text;
    private Set<AnswerDTO> answers;
    private boolean secondType;
    private boolean thirdType;
    private String choices;


    public QuestionDTO(Question question)
    {
        this.id = question.getId();
        this.text = question.getText();
        for( Answer a : question.getAnswers())
        {
            answers.add(new AnswerDTO(a));
        }
        this.secondType = question.isSecondType();
        this.thirdType = question.isThirdType();
        this.choices = question.getChoices();
    }
}
