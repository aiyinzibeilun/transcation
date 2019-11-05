package com.notice.bank1.service;


import com.notice.bank1.entity.AccountPay;
import com.notice.bank1.model.AccountChangeEvent;

public interface AccountService {

    void updateBalance(AccountChangeEvent accountChangeEvent);

    public AccountPay getAccoutResoult(int txNo);

}
