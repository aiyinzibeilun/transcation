package com.notice.bank1.controller;


import com.notice.bank1.model.AccountChangeEvent;
import com.notice.bank1.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/bank1")
@Slf4j
public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping("/transfor")
    public String transfor(Double paymoney){
        String txNo = UUID.randomUUID().toString();
        log.info("事务id为txNo:{}",txNo);
        AccountChangeEvent accountChangeEvent = new AccountChangeEvent("1", paymoney, txNo);
       //发送消息给mq
        accountService.sendMsgToMq(accountChangeEvent);
        return "转账成功"+paymoney;
    }
}
