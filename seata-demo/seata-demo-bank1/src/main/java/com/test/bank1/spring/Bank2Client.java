package com.test.bank1.spring;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "bank2" ,fallback = Bank2Callback.class)
public interface Bank2Client {
    @GetMapping("bank2/addMoney")
    public String addMoney(@RequestParam("paymoney")Double paymoney);

}
