package com.ftn.ZgradeProjekat.service;

import com.ftn.ZgradeProjekat.domain.DTO.BugDTO;
import com.ftn.ZgradeProjekat.domain.DTO.CommentDTO;

import java.util.List;

/**
 * Created by djuro on 11/29/2017.
 */
public interface BugService
{
    BugDTO reportBug(Long locationId, BugDTO bugDTO);

    List<BugDTO> getAllBugs(Long locationId);

    Boolean deleteBug(Long bugId, Long locationId);

    CommentDTO addComment(CommentDTO commentDTO, Long bugId);

    BugDTO getBug(Long bugId);

    Boolean deleteComment(Long id, Long bugId);

    List<BugDTO> getBugsOfResponsiblePerson(Integer Id);

    Boolean connectBugAndFirm(Long bugId, Integer firmId);

    List<BugDTO> getBugsOfFirm(Integer firmId);

    Boolean payBill(Long bugId);
}
