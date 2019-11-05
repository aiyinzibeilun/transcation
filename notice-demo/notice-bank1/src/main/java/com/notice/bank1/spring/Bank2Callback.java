package com.notice.bank1.spring;


import com.notice.bank1.entity.AccountPay;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class Bank2Callback implements Bank2Client {

    public AccountPay getAccountResult(@RequestParam("txNo")int txNo){
        AccountPay accountPay = new AccountPay();
        accountPay.setResult("fail");
        return accountPay;
    }
}
