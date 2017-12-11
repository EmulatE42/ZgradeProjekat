package com.ftn.ZgradeProjekat.domain;

import com.ftn.ZgradeProjekat.domain.DTO.AnswerDTO;
import com.ftn.ZgradeProjekat.domain.DTO.QuestionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by djuro on 11/4/2017.
 */
@Entity
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Getter
@Setter
@Table(name = "question")
public class Question
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "question_text")
    private String text;

    @OneToMany
    private Set<Answer> answers;

    @Column(name = "secondType")
    private boolean secondType;

    @Column(name = "thirdType")
    private boolean thirdType;

    @Column(name = "choices")
    private String choices; // odvojeni zarezom da je slucaj 3


    public Question(QuestionDTO questionDTO)
    {
        this.text = questionDTO.getText();
        this.secondType = questionDTO.isSecondType();
        this.thirdType = questionDTO.isThirdType();
        this.choices = questionDTO.getChoices();
        for (AnswerDTO a: questionDTO.getAnswers())
        {
            this.answers.add(new Answer(a));
        }

    }

    public Integer getTypeOfAnswer() {
        if (secondType) return 2;
        else if (thirdType) return 3;
        return 1;
    }


    public Double averageRateofSecondType() {
        if (this.isSecondType())
        {
            int sum = 0;
            for(Answer a : this.getAnswers())
            {
                sum+=a.getRate();
            }

            return (double)sum / this.getAnswers().size();
        }
        return null;

    }
    // slucaj 1 je slobodan tekst kao odgovor

    // slucaj 2 je da doabere ocenu od 1 do 5 kao radio button

    // slucaj 3 je da ima padajuci meni odakle bira ponjudjen odgovor

}


