package com.ftn.ZgradeProjekat.service.implementation;

import com.ftn.ZgradeProjekat.domain.Bill;
import com.ftn.ZgradeProjekat.domain.Bug;
import com.ftn.ZgradeProjekat.repository.*;
import com.ftn.ZgradeProjekat.service.BillService;
import com.ftn.ZgradeProjekat.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by djuro on 12/3/2017.
 */
@Service
public class BillServiceImpl implements BillService
{
    private final BugRepository bugRepository;
    private final BillRepository billRepository;


    private final LocationRepository locationRepository;
    private final ResponsiblePersonRepository responsiblePersonRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final InstitutionRepository institutionRepository;

    @Autowired
    public BillServiceImpl(BugRepository bugRepository,
                          LocationRepository locationRepository,
                          ResponsiblePersonRepository responsiblePersonRepository,
                          UserRepository userRepository,
                          CommentRepository commentRepository,
                          InstitutionRepository institutionRepository,
                           BillRepository billRepository)
    {
        this.bugRepository = bugRepository;
        this.billRepository = billRepository;

        this.locationRepository = locationRepository;
        this.responsiblePersonRepository = responsiblePersonRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.institutionRepository = institutionRepository;
    }

    @Override
    public Bill makeBill(Long bugId, Bill bill)
    {
        Bug bug = null;
        bug = this.bugRepository.findOne(bugId);
        if(bug != null)
        {
            this.billRepository.save(bill);
            bug.setBill(bill);
            bug.setFinished(true);
            this.bugRepository.save(bug);
            return bill;
        }
        return null;
    }

    @Override
    public Bill getBill(Long bugId)
    {
        Bug bug = null;
        bug = this.bugRepository.findOne(bugId);
        Bill bill = null;
        if(bug != null)
        {
            bill = bug.getBill();
        }
        return bill;
    }
}
