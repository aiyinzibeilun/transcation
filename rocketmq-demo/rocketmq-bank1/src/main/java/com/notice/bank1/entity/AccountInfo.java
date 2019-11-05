package com.notice.bank1.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Data
@Getter
@Setter
@ToString
public class AccountInfo {

    private int id ;

    private String accountName;

    private String accountNo;

    private double accountBalance;

}
