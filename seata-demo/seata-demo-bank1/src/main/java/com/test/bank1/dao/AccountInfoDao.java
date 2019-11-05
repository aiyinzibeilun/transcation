package com.test.bank1.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AccountInfoDao {
    @Update("update account_info set account_balance=account_balance+ #{paymoney}  where account_no=#{accountNo} and account_balance>0")
    int cheakAndUpdateBalance(@Param("accountNo") String accountNo, @Param("paymoney") Double paymoney);
}
