package com.notice.bank1.message;


import com.notice.bank1.entity.AccountPay;
import com.notice.bank1.model.AccountChangeEvent;
import com.notice.bank1.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Administrator
 * @date: 2019/11/3
 */
@Slf4j
@RocketMQMessageListener(consumerGroup = "consumer_group_txmsg_bank2",topic = "topic_txmsg")
@Component
public class TxMsgComsumer implements RocketMQListener<AccountPay> {
    @Autowired
    AccountService accountService;

    public void onMessage(AccountPay accountPay) {
        log.info("处理消息");
        if("success".equals(accountPay.getResult())){
            AccountChangeEvent accountChangeEvent = new AccountChangeEvent();
            accountChangeEvent.setAccountNo(accountPay.getAccountNo());
            accountChangeEvent.setAmount(accountPay.getPayAmount());
            accountChangeEvent.setTxNo(""+accountPay.getId());
            accountService.updateBalance(accountChangeEvent);
        }
    }
}
