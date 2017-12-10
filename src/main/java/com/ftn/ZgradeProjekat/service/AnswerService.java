package com.ftn.ZgradeProjekat.service;

import com.ftn.ZgradeProjekat.domain.Answer;
import com.ftn.ZgradeProjekat.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by EmulatE on 09-Dec-17.
 */
public interface AnswerService {

    Answer save(Answer answer);

    void delete(Integer id);

}
