package com.notice.bank1.service.impl;

import com.notice.bank1.dao.AccountInfoDao;
import com.notice.bank1.entity.AccountPay;
import com.notice.bank1.model.AccountChangeEvent;
import com.notice.bank1.service.AccountService;
import com.notice.bank1.spring.Bank2Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountInfoDao accountInfoDao;
    @Autowired
    private Bank2Client bank2Client;
    @Transactional
    public void updateBalance(AccountChangeEvent accountChangeEvent) {
        if (accountInfoDao.isExistTx(accountChangeEvent.getTxNo())>0) {
            return;
        }
        accountInfoDao.substractBalance("1",accountChangeEvent.getAmount());
        accountInfoDao.addTx(accountChangeEvent.getTxNo(),"1");
    }


    public AccountPay getAccoutResoult(int txNo) {
        AccountPay accountResult = bank2Client.getAccountResult(txNo);
        if (accountResult.getResult().equals("success")) {
            AccountChangeEvent accountChangeEvent = new AccountChangeEvent();
            accountChangeEvent.setAccountNo(accountResult.getAccountNo());//账号
            accountChangeEvent.setAmount(accountResult.getPayAmount());//金额
            accountChangeEvent.setTxNo(""+accountResult.getId());//充值事务号
            updateBalance(accountChangeEvent);
        }
        return accountResult;
    }
}
