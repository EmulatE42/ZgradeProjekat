package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.Answer;
import com.ftn.ZgradeProjekat.domain.Question;
import com.ftn.ZgradeProjekat.repository.QuestionRepository;
import com.ftn.ZgradeProjekat.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * Created by EmulatE on 09-Dec-17.
 */
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public void delete(Integer id) {
        questionRepository.delete(id);
    }

    @Override
    public Double averageRateofSecondType(Question question) {
        if (question.isSecondType())
        {
            int sum = 0;
            for(Answer a : question.getAnswers())
            {
                sum+=a.getRate();
            }

            return (double)sum / question.getAnswers().size();
        }
        return null;

    }


}
