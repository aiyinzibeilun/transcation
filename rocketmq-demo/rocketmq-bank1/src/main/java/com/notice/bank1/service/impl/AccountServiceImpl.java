package com.notice.bank1.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.notice.bank1.model.AccountChangeEvent;
import com.notice.bank1.service.AccountService;
import com.notice.bank1.dao.AccountInfoDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountInfoDao accountInfoDao;
    @Autowired
     RocketMQTemplate rocketMQTemplate;

    public void sendMsgToMq(AccountChangeEvent accountChangeEvent) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accountChange",accountChangeEvent);
        Message<String> message = MessageBuilder.withPayload(jsonObject.toJSONString()).build();
        /**
         * String txProducerGroup 生产组
         * String destination topic，
         * Message<?> message, 消息内容
         * Object arg 参数
         */
        rocketMQTemplate.sendMessageInTransaction("producer_group_txmsg_bank1","topic_txmsg",message,null);

    }

    @Transactional
    public void substractBalance(AccountChangeEvent accountChangeEvent) {
        //进行幂等性判断
        if (accountInfoDao.isExistTx(accountChangeEvent.getTxNo())>0) {
            return;
        }
        //操作本地事务
        accountInfoDao.substractBalance(accountChangeEvent.getAccountNo(),accountChangeEvent.getAmount());
        log.info("张三减钱成功！");
        //添加事务记录
        accountInfoDao.addTx(accountChangeEvent.getTxNo(),accountChangeEvent.getAccountNo());
    }
}
