package com.notice.bank1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Administrator
 * @date: 2019/11/3
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountPay {
   private int id;
   private String accountNo;
   private Double payAmount;
   private String result;
}
