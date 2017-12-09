package com.ftn.ZgradeProjekat.service;

import com.ftn.ZgradeProjekat.domain.Bill;

/**
 * Created by djuro on 12/3/2017.
 */
public interface BillService
{
    Bill makeBill(Long bugId, Bill bill);

    Bill getBill(Long bugId);
}
