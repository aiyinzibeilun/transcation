package com.test.bank2.service.impl;


import com.test.bank2.dao.AccountInfoDao;
import com.test.bank2.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.Hmily;
import org.dromara.hmily.core.concurrent.threadlocal.HmilyTransactionContextLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountInfoDao accountInfoDao;


    @Hmily(confirmMethod = "commit",cancelMethod = "rollback")
    public void cheakAndUpdateBalance(String accountNo, Double paymoney) {
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("全局事务Xid{}",transId);
    }

    @Transactional
    public void commit(String accountNo, Double paymoney){
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("commit阶段：全局事务Xid:{},accountNo:{},paymoney:{}",transId,accountNo,paymoney);
        if (accountInfoDao.isExistConfirm(transId)>0) {
            log.info("commit阶段：已经执行，全局事务Xid:{},accountNo:{},paymoney:{}",transId,accountNo,paymoney);
            return;
        }
        accountInfoDao.addBalance(accountNo,paymoney);
        log.info("李四增加金额完成！");
        accountInfoDao.addConfirm(transId);
    }

    public void rollback(String accountNo, Double paymoney){
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("rollback阶段：全局事务Xid:{},accountNo:{},paymoney:{}",transId,accountNo,paymoney);
    }
}
