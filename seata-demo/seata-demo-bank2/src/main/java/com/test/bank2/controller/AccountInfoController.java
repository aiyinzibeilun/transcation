package com.test.bank2.controller;

import com.test.bank2.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank2")
public class AccountInfoController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/addMoney")
    public String addMoney(Double paymoney){
        accountService.cheakAndUpdateBalance("2",paymoney);
        return "收到"+paymoney;
    }
}
