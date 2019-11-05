package com.notice.bank2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: Administrator
 * @date: 2019/11/3
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class AccountChangeEvent implements Serializable {
    private String accountNo;
    private Double amount;
    private String txNo;
}
