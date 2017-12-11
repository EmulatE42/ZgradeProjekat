package com.ftn.ZgradeProjekat.service;

import com.ftn.ZgradeProjekat.domain.Answer;
import com.ftn.ZgradeProjekat.domain.DTO.QuestionDTO;
import com.ftn.ZgradeProjekat.domain.Question;

import java.util.List;
import java.util.Set;

/**
 * Created by EmulatE on 09-Dec-17.
 */
public interface QuestionService {

    QuestionDTO save(QuestionDTO questionDTO);
    void delete(Integer id);
}
