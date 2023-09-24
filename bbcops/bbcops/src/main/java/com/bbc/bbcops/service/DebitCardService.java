package com.bbc.bbcops.service;

import org.springframework.stereotype.Service;

import com.bbc.bbcops.dao.DebitCardDao;
import com.bbc.bbcops.model.DebitCard;

@Service
public class DebitCardService {

    private DebitCardDao debitCardDao;

    public DebitCardService(DebitCardDao debitCardDao) {
        this.debitCardDao = debitCardDao;
    }

    public boolean isDebitCardValid(Long customerId, DebitCard debitCard) {
        return debitCardDao.isDebitCardValid(customerId, debitCard);
    }

    public String payBillByDebitCard(Long customerId, Long billId) {
        return debitCardDao.payBillByDebitCard(customerId, billId);
    }
}
