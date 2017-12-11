package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.Answer;
import com.ftn.ZgradeProjekat.domain.DTO.QuestionDTO;
import com.ftn.ZgradeProjekat.domain.Question;
import com.ftn.ZgradeProjekat.repository.QuestionRepository;
import com.ftn.ZgradeProjekat.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by EmulatE on 09-Dec-17.
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    @Override
    public QuestionDTO save(QuestionDTO questionDTO) {

        questionRepository.save(new Question(questionDTO));


        return questionDTO;
    }

    @Override
    public void delete(Integer id) {
        questionRepository.delete(id);
    }



}
