package com.test.bank1.service.impl;

import com.test.bank1.dao.AccountInfoDao;
import com.test.bank1.service.AccountService;
import com.test.bank1.spring.Bank2Client;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
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
    @GlobalTransactional
    public void cheakAndUpdateBalance(String accountNo, Double paymoney) {
        log.info("全局事务Xid{}", RootContext.getXID());
        accountInfoDao.cheakAndUpdateBalance(accountNo, paymoney * (-1));
        String rtnMsg = bank2Client.addMoney(paymoney);
        if ("fail".equals(rtnMsg)) {
            throw new RuntimeException("调用远程服务异常！");
        }
        if (paymoney == 2.0) {
            throw new RuntimeException("人为异常");
        }

    }
}
