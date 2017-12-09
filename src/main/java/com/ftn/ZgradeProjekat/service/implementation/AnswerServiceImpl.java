package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.Answer;
import com.ftn.ZgradeProjekat.repository.AnswerRepository;
import com.ftn.ZgradeProjekat.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by EmulatE on 09-Dec-17.
 */
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Override
    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public void delete(Integer id) {
        answerRepository.delete(id);
    }
}
