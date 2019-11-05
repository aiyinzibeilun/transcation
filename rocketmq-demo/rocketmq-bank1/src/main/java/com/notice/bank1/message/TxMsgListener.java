package com.notice.bank1.message;

import com.alibaba.fastjson.JSONObject;
import com.notice.bank1.dao.AccountInfoDao;
import com.notice.bank1.model.AccountChangeEvent;
import com.notice.bank1.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author: Administrator
 * @date: 2019/11/3
 */
@Component
@Slf4j
@RocketMQTransactionListener(txProducerGroup = "producer_group_txmsg_bank1")
public class TxMsgListener implements RocketMQLocalTransactionListener {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountInfoDao accountInfoDao;

    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        try {
            //解析message，转成AccountChangeEvent
            String messageString = new String((byte[]) message.getPayload());
            JSONObject jsonObject = JSONObject.parseObject(messageString);
            String accountChangeString = jsonObject.getString("accountChange");
            //将accountChange（json）转成AccountChangeEvent
            AccountChangeEvent accountChangeEvent = JSONObject.parseObject(accountChangeString, AccountChangeEvent.class);
            //执行本地事务，扣减金额
            accountService.substractBalance(accountChangeEvent);
            //当返回RocketMQLocalTransactionState.COMMIT，自动向mq发送commit消息，mq将消息的状态改为可消费
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            e.printStackTrace();
            return RocketMQLocalTransactionState.ROLLBACK;
        }


    }

    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        String s = new String((byte[]) message.getPayload());
        JSONObject jsonObject = JSONObject.parseObject(s);
        String accountChange = jsonObject.getString("accountChange");
        AccountChangeEvent accountChangeEvent = JSONObject.parseObject(accountChange, AccountChangeEvent.class);
        if (accountInfoDao.isExistTx(accountChangeEvent.getTxNo())>0) {
            return RocketMQLocalTransactionState.COMMIT;
        }else {
            return RocketMQLocalTransactionState.UNKNOWN;
        }

    }
}
