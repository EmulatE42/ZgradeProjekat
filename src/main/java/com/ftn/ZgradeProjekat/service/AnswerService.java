package com.ftn.ZgradeProjekat.service;

import com.ftn.ZgradeProjekat.domain.Answer;
import com.ftn.ZgradeProjekat.domain.DTO.AnswerDTO;
import com.ftn.ZgradeProjekat.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by EmulatE on 09-Dec-17.
 */
public interface AnswerService {

    AnswerDTO save(AnswerDTO answerDTO);

    void delete(Long id);

    List<AnswerDTO> getAllAnswers();

    void updateAnswer(Long a,Long id);
}
