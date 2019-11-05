package com.notice.bank1.spring;

import com.notice.bank1.entity.AccountPay;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "notice-bank2" ,fallback = Bank2Callback.class)
public interface Bank2Client {
    @GetMapping("bank2/getAccountResult")

    public AccountPay getAccountResult(@RequestParam("txNo")int txNo);

}
