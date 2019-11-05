package com.notice.bank2.service;


import com.notice.bank2.entity.AccountPay;

public interface AccountService {

   AccountPay insertAccountPay(AccountPay accountPay);

   AccountPay findByTxNo(int txNo);

}
