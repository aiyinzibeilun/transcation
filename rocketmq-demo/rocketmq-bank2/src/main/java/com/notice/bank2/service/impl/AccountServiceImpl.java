package com.notice.bank2.service.impl;


import com.notice.bank2.model.AccountChangeEvent;
import com.notice.bank2.dao.AccountInfoDao;
import com.notice.bank2.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountInfoDao accountInfoDao;

    @Transactional
    public void addBalance(AccountChangeEvent accountChangeEvent) {
        //进行幂等性判断
        if (accountInfoDao.isExistTx(accountChangeEvent.getTxNo())>0) {
            return;
        }
        //操作本地事务
        accountInfoDao.addBalance(accountChangeEvent.getAccountNo(),accountChangeEvent.getAmount());
        //添加事务记录
        accountInfoDao.addTx(accountChangeEvent.getTxNo(),accountChangeEvent.getAccountNo());
    }
}
