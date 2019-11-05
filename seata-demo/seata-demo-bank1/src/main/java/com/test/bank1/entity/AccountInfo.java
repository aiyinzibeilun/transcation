package com.test.bank1.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


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
