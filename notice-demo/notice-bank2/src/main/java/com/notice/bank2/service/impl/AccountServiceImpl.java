package com.notice.bank2.service.impl;


import com.alibaba.fastjson.JSONObject;

import com.notice.bank2.dao.AccountInfoDao;
import com.notice.bank2.entity.AccountPay;
import com.notice.bank2.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountInfoDao accountInfoDao;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Transactional
    public AccountPay insertAccountPay(AccountPay accountPay) {
        int insertAccountPay = accountInfoDao.insertAccountPay(accountPay.getId(), accountPay.getAccountNo()
                , accountPay.getPayAmount(), accountPay.getResult());
        if (insertAccountPay>0) {
            accountPay.setResult("success");
            rocketMQTemplate.convertAndSend("topic_txmsg",accountPay);
            return accountPay;
        }
        return null;
    }

    //查询充值记录
    public AccountPay findByTxNo(int txNo) {
        AccountPay byTxNo = accountInfoDao.findByTxNo(txNo);
        return byTxNo;
    }
}
