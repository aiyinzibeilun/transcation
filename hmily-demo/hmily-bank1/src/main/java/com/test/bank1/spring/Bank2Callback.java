package com.test.bank1.spring;


import org.springframework.stereotype.Component;

@Component
public class Bank2Callback implements Bank2Client {

    public String addMoney(Double paymoney) {
        return "fail";
    }
}
