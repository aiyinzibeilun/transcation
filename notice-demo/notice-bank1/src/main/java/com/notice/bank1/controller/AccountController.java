package com.notice.bank1.controller;


import com.notice.bank1.entity.AccountPay;
import com.notice.bank1.model.AccountChangeEvent;
import com.notice.bank1.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/bank1")
@Slf4j
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/getResult")
    public AccountPay getResult(@RequestParam("txNo")int txNo){
        log.info("txNo:{}",txNo);
        return accountService.getAccoutResoult(txNo);
    }
}
