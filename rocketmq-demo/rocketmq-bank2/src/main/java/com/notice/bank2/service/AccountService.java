package com.notice.bank2.service;


import com.notice.bank2.model.AccountChangeEvent;

public interface AccountService {

    void addBalance(AccountChangeEvent accountChangeEvent);

}
