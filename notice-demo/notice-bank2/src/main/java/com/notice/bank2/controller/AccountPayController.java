package com.notice.bank2.controller;

import com.notice.bank2.entity.AccountPay;
import com.notice.bank2.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank2")
public class AccountPayController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/insertAccountPay")
    public AccountPay insertAccountPay(AccountPay accountPay){
       return accountService.insertAccountPay(accountPay);
    }

    @GetMapping("/getAccountResult")
    public AccountPay getAccountResult(@RequestParam("txNo")int txNo){
        return accountService.findByTxNo(txNo);

    }
}
