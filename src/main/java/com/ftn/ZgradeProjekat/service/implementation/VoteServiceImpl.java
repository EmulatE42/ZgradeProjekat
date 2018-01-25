package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.DTO.TopicDTO;
import com.ftn.ZgradeProjekat.domain.DTO.VoteDTO;
import com.ftn.ZgradeProjekat.domain.Topic;
import com.ftn.ZgradeProjekat.domain.Vote;
import com.ftn.ZgradeProjekat.repository.VoteRepository;
import com.ftn.ZgradeProjekat.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Momir on 16.01.2018.
 */
@Service
public class VoteServiceImpl implements VoteService{

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public VoteDTO addVote(Vote vote) {

        Vote saved = this.voteRepository.save(vote);

        VoteDTO voteDTO = new VoteDTO(saved);
        return voteDTO;
    }

    @Override
    public VoteDTO getVote(Long topic_id, Integer tenant_id) {

        Vote vote = this.voteRepository.getVote(topic_id, tenant_id);
        if(vote != null) {
            VoteDTO voteDTO = new VoteDTO(vote);
            return voteDTO;
        }
        else
            return null;
    }
}
