package com.ftn.ZgradeProjekat.service;

import com.ftn.ZgradeProjekat.domain.DTO.VoteDTO;
import com.ftn.ZgradeProjekat.domain.Vote;

/**
 * Created by Momir on 16.01.2018.
 */
public interface VoteService {

    VoteDTO addVote(Vote vote);
    VoteDTO getVote(Long topic_id, Integer tenant_id);
}
