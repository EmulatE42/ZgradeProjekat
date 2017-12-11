package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.Answer;
import com.ftn.ZgradeProjekat.domain.DTO.AnswerDTO;
import com.ftn.ZgradeProjekat.repository.AnswerRepository;
import com.ftn.ZgradeProjekat.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EmulatE on 09-Dec-17.
 */
@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerRepository answerRepository;


    @Override
    public AnswerDTO save(AnswerDTO answerDTO) {

        answerRepository.save(new Answer(answerDTO));
        return answerDTO;
    }

    @Override
    public void delete(Integer id) {
        answerRepository.delete(id);
    }

    @Override
    public List<AnswerDTO> getAllAnswers() {
        List<Answer> a = answerRepository.findAll();

        List<AnswerDTO> rez = new ArrayList<>();
        for (Answer a1 : a)
        {
            rez.add( new AnswerDTO(a1));
        }
        return  rez;
    }
}
