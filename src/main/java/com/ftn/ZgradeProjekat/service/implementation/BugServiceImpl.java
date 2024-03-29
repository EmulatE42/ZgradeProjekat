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
import java.util.Set;

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
    private final InstitutionRepository institutionRepository;
    private final FirmRepository firmRepository;

    @Autowired
    public BugServiceImpl(BugRepository bugRepository,
                          LocationRepository locationRepository,
                          ResponsiblePersonRepository responsiblePersonRepository,
                          UserRepository userRepository,
                          CommentRepository commentRepository,
                          InstitutionRepository institutionRepository,
                          FirmRepository firmRepository)
    {
        this.bugRepository = bugRepository;
        this.locationRepository = locationRepository;
        this.responsiblePersonRepository = responsiblePersonRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.institutionRepository = institutionRepository;
        this.firmRepository = firmRepository;
    }

    /**
     *
     * @param locationId - id lokacije gde se kvar dogodio
     * @param bugDTO - kvar koji se desio
     * @return - kvar koji je prijavljen
     */
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
            responsiblePerson.addBug(bug);
            this.responsiblePersonRepository.save(responsiblePerson);
            bugDTO.setId(bug.getId());
            location.addBug(bug);
            this.locationRepository.save(location);
        }
        else bugDTO=null;
        return bugDTO;
    }

    /**
     *
     * @param locationId - lokacija za koju zelimo da dobavimo sve kvarove
     * @return - lista svih kvarova za datu lokaciju
     */
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

    /**
     *
     * @param bugId - id kvara koji se brise
     * @param locationId - lokacija gde se kvar desio
     * @return - bool u zavisnosti od uspesnosti brisanja
     */
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
            Building building = location.getBuilding();
            for(ResponsiblePerson rp : building.getResponsiblePersons())
            {
                if(rp.getBugs().contains(bug))
                {
                    rp.removeBug(bug);
                    this.responsiblePersonRepository.save(rp);
                    break;
                }
            }
            List<Firm> firms  = this.firmRepository.findAll();
            for(Firm f : firms)
            {
                if(f.getBugs().contains(bug))
                {
                    f.removeBug(bug);
                    this.firmRepository.save(f);
                    break;
                }
            }
            this.locationRepository.save(location);
            this.bugRepository.delete(bug);
            deleted = true;
        }
        return deleted;
    }

    /**
     *
     * @param commentDTO - komentar koji se dodaje
     * @param bugId - kvar za koji je dodat komentar
     * @return - komentar koji je upisan
     */
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

    /**
     *
     * @param bugId - za dati Id dobavlja se kvar
     * @return kvar koji se dobavio
     */
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

    /**
     *
     * @param id - id komentara koji se brise
     * @param bugId - kvar za koji se brise komentar
     * @return - bool u zavinosti od brisanja komentara
     */
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

    /**
     *
     * @param userId - id odgovornog lica cije kvarove zelimo da dobavimo
     * @return - lista svih kvarova odgovornog lica
     */
    @Override
    public List<BugDTO> getBugsOfResponsiblePerson(Integer userId)
    {
        Set<ResponsiblePerson> responsiblePeople = null;
        responsiblePeople = this.responsiblePersonRepository.findAllResponsiblePeopleByUserId(userId);
        List<BugDTO> bugs = null;
        if(responsiblePeople != null)
        {
            for(ResponsiblePerson rp : responsiblePeople)
            {
                bugs = new ArrayList<>();
                for(Bug bug : rp.getBugs())
                {
                    bugs.add(new BugDTO(bug));
                }
            }
        }
        return bugs;
    }

    /**
     *
     * @param bugId - id kvara koji se salje firmi
     * @param firmId - id firme koja dobija kvar
     * @return - bool u zavinosti od uspesnosti komunikacije
     */
    @Override
    public Boolean connectBugAndFirm(Long bugId, Integer firmId)
    {
        Bug bug = this.bugRepository.findOne(bugId);
        Firm firm = this.firmRepository.findOne(firmId);
        Boolean connected = false;
        if(bug != null && firm != null)
        {
            if(bug.getResponsibleFirm()!=null)
            {
                Firm tempFirm = bug.getResponsibleFirm();
                tempFirm.removeBug(bug);
                this.firmRepository.save(tempFirm);
                bug.setResponsibleFirm(firm);
                this.bugRepository.save(bug);
            }
            else
            {
                bug.setResponsibleFirm(firm);
                this.bugRepository.save(bug);
            }

            firm.addBug(bug);
            this.firmRepository.save(firm);
            connected = true;
        }
        return connected;
    }

    /**
     *
     * @param firmId - id firme cije kvarove zelimo da vidimo
     * @return - lista kvarova za datu firmu
     */
    @Override
    public List<BugDTO> getBugsOfFirm(Integer firmId)
    {
        Firm firm = this.firmRepository.findOne(firmId);
        List<BugDTO> bugDTOs = null;
        if(firm != null)
        {
            bugDTOs = new ArrayList<>();
            for(Bug bug : firm.getBugs())
            {
                bugDTOs.add(new BugDTO(bug));
            }
        }
        return bugDTOs;
    }

    /**
     *
     * @param bugId - id kvara za koji se placa racun
     * @return -- bool u zavisnosti od uspesnosti placanja
     */
    @Override
    public Boolean payBill(Long bugId)
    {
        Bug bug = null;
        Boolean paid = false;
        bug = this.bugRepository.findOne(bugId);
        if(bug != null)
        {
            bug.setPaid(true);
            this.bugRepository.save(bug);
            paid = true;
        }
        return paid;
    }
}
