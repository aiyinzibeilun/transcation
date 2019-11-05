package com.test.bank2.service.impl;

import com.test.bank2.dao.AccountInfoDao;
import com.test.bank2.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountInfoDao accountInfoDao;

    @Override
    @Transactional
    public void cheakAndUpdateBalance(String accountNo, Double paymoney) {
        log.info("李四修改修改金额{}", paymoney);
        accountInfoDao.cheakAndUpdateBalance(accountNo, paymoney);
//        if (paymoney==2.0) {
//            new RuntimeException("人为异常");
//        }

    }
}
