package com.bbc.bbcops.controller;

import org.springframework.web.bind.annotation.*;

import com.bbc.bbcops.model.DebitCard;
import com.bbc.bbcops.service.DebitCardService;

@CrossOrigin()
@RestController
@RequestMapping("/bbc/customer/bills")
public class DebitCardController {

    private DebitCardService debitCardService;

    public DebitCardController(DebitCardService debitCardService) {
        this.debitCardService = debitCardService;
    }

    @PostMapping("/{customerId}/check-debit-card")
    public boolean checkDebitCardExistence(@PathVariable Long customerId, @RequestBody DebitCard debitCard) {
        return debitCardService.isDebitCardValid(customerId, debitCard);
    }

    @PutMapping("/{customerId}/{billId}/debit-card-pay")
    public String payBillByDebitCard(@PathVariable Long customerId, @PathVariable Long billId) {
        return debitCardService.payBillByDebitCard(customerId, billId);
    }
}
