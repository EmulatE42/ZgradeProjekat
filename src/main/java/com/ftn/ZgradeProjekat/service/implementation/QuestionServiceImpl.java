package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.Answer;
import com.ftn.ZgradeProjekat.domain.DTO.QuestionDTO;
import com.ftn.ZgradeProjekat.domain.Question;
import com.ftn.ZgradeProjekat.repository.QuestionRepository;
import com.ftn.ZgradeProjekat.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by EmulatE on 09-Dec-17.
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    /**
     *
     * @param questionDTO - pitanje koje se dodaje
     * @return - dodato pitanje
     */
    @Override
    public QuestionDTO save(QuestionDTO questionDTO) {

        questionRepository.save(new Question(questionDTO));


        return questionDTO;
    }

    /**
     *
     * @param id - id pitanja koji se brise
     *
     */
    @Override
    public void delete(Long id) {
        questionRepository.delete(id);
    }

    /**
     *
     * @return - lista svih pitanja
     */
    @Override
    public List<QuestionDTO> getAllQuestions() {
        List<Question> a = questionRepository.findAll();

        List<QuestionDTO> rez = new ArrayList<>();
        for (Question a1 : a)
        {
            rez.add( new QuestionDTO(a1));
        }
        return  rez;
    }


}
