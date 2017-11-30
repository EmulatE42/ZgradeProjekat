package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.*;
import com.ftn.ZgradeProjekat.domain.DTO.BugDTO;
import com.ftn.ZgradeProjekat.domain.DTO.CommentDTO;
import com.ftn.ZgradeProjekat.repository.*;
import com.ftn.ZgradeProjekat.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by djuro on 11/29/2017.
 */
@Service
public class BugServiceImpl implements BugService
{
    private final BugRepository bugRepository;
    private final LocationRepository locationRepository;
    private final ResponsiblePersonRepository responsiblePersonRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public BugServiceImpl(BugRepository bugRepository,
                          LocationRepository locationRepository,
                          ResponsiblePersonRepository responsiblePersonRepository,
                          UserRepository userRepository,
                          CommentRepository commentRepository)
    {
        this.bugRepository = bugRepository;
        this.locationRepository = locationRepository;
        this.responsiblePersonRepository = responsiblePersonRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public BugDTO reportBug(Long locationId, BugDTO bugDTO)
    {
        Location location = this.locationRepository.findById(locationId);
        if(location!=null)
        {
            Bug bug = new Bug(bugDTO);
            ResponsiblePerson responsiblePerson = this.responsiblePersonRepository.findOne(bugDTO.getResponsiblePersonDTO().getId());
            bug.setResponsiblePerson(responsiblePerson);
            this.bugRepository.save(bug);
            bugDTO.setId(bug.getId());
            location.addBug(bug);
            this.locationRepository.save(location);
        }
        else bugDTO=null;
        return bugDTO;
    }

    @Override
    public List<BugDTO> getAllBugs(Long locationId)
    {
        Location location = null;
        location = this.locationRepository.findById(locationId);
        if(location != null)
        {
            List<BugDTO> bugDTOs = new ArrayList<>();
            for(Bug bug : location.getBugs())
            {
                bugDTOs.add(new BugDTO(bug));
            }
            return bugDTOs;
        }
        return null;
    }

    @Override
    public Boolean deleteBug(Long bugId, Long locationId)
    {
        Bug bug = null;
        Boolean deleted = false;
        bug = this.bugRepository.findOne(bugId);
        if(bug!=null)
        {
            Location location = this.locationRepository.findById(locationId);
            location.removeBug(bug);
            this.locationRepository.save(location);
            this.bugRepository.delete(bug);
            deleted = true;
        }
        return deleted;
    }

    @Override
    public CommentDTO addComment(CommentDTO commentDTO, Long bugId)
    {
        Bug bug = null;
        bug = this.bugRepository.findOne(bugId);
        if(bug != null)
        {
            Comment comment = new Comment(commentDTO);
            User user = this.userRepository.getOne(commentDTO.getUser().getId());
            comment.setCreator(user);
            this.commentRepository.save(comment);
            bug.addComment(comment);
            this.bugRepository.save(bug);
            commentDTO.setId(comment.getId());
        }
        else{
            commentDTO = null;
        }
        return commentDTO;
    }

    @Override
    public BugDTO getBug(Long bugId)
    {
        Bug bug = null;
        bug = this.bugRepository.findOne(bugId);
        BugDTO bugDTO = null;
        if(bug != null)
        {
            bugDTO = new BugDTO(bug);
        }
        return bugDTO;
    }

    @Override
    public Boolean deleteComment(Long id, Long bugId)
    {
        Comment comment = null;
        Boolean deleted = false;
        comment = this.commentRepository.getOne(id);
        if(comment != null)
        {
            Bug bug = this.bugRepository.findOne(bugId);
            bug.removeComment(comment);
            this.bugRepository.save(bug);
            this.commentRepository.delete(comment);
            deleted = true;
        }
        return deleted;
    }
}
