package com.ftn.ZgradeProjekat.domain.DTO;

import com.ftn.ZgradeProjekat.domain.Vote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Momir on 16.01.2018.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoteDTO {

    private Integer id;
    private TopicDTO topicDTO;
    private TenantDTO tenantDTO;


    public VoteDTO(Vote vote)
    {
        this.id = vote.getId();
        this.tenantDTO = new TenantDTO(vote.getTenant());
        this.topicDTO = new TopicDTO(vote.getTopic());
    }
}
