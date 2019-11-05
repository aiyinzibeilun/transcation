package com.test.bank1.controller;


import com.test.bank1.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank1")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping("/transfor")
    public String transfor(Double paymoney){
        accountService.cheakAndUpdateBalance("1",paymoney);
        return "转账成功"+paymoney;
    }
}
