package com.notice.bank1.service;


import com.notice.bank1.model.AccountChangeEvent;

public interface AccountService {
    void sendMsgToMq(AccountChangeEvent accountChangeEvent);
    void substractBalance(AccountChangeEvent accountChangeEvent);

}
