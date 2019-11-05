package com.test.bank1.service.impl;


import com.test.bank1.service.AccountService;
import com.test.bank1.spring.Bank2Client;
import com.test.bank1.dao.AccountInfoDao;
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
    @Autowired
    private Bank2Client bank2Client;

    @Transactional
    @Hmily(confirmMethod = "commit",cancelMethod = "rollback")
    public void cheakAndUpdateBalance(String accountNo, Double paymoney) {
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("全局事务Xid{}",transId);
        //检验try的幂等性
        if (accountInfoDao.isExistTry(transId)>0) {
            log.info("bank1 try 已经执行，无需重复执行,xid:{}",transId);
            return;
        }
        //进行悬挂处理
        if(accountInfoDao.isExistCancel(transId)>0 || accountInfoDao.isExistConfirm(transId)>0){
            log.info("bank1的try进行悬挂处理, confirm  cancel阶段已执行，不允许执行xid:{}",transId);
            return;
        }

        if (accountInfoDao.substractBalance(accountNo, paymoney)<=0) {
           throw new RuntimeException("bank1扣减金额失败！");
        }
        //插入try阶段日志
        accountInfoDao.addTry(transId);

        String rtnMsg = bank2Client.addMoney(paymoney);
        if ("fail".equals(rtnMsg)) {
            throw new RuntimeException("调用远程服务异常！");
        }
        if (paymoney == 2.0) {
            throw new RuntimeException("人为异常");
        }

    }

    public void commit(String accountNo, Double paymoney){
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("commit阶段：全局事务Xid:{},accountNo:{},paymoney:{}",transId,accountNo,paymoney);
    }

    @Transactional
    public void rollback(String accountNo, Double paymoney){
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        log.info("rollback阶段：全局事务Xid:{},accountNo:{},paymoney:{}",transId,accountNo,paymoney);
        //幂等性校验
        if (accountInfoDao.isExistCancel(transId)>0) {
            log.info("bank1  cancel已经执行");
            return;
        }

        if (accountInfoDao.isExistTry(transId)<=0) {
            log.info("防止空回滚，xid:{}",transId);
            return;
        }
        accountInfoDao.addBalance(accountNo,paymoney);
        accountInfoDao.addCancel(transId);
    }
}
