package com.notice.bank1.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AccountInfoDao {
    @Update("update account_info set account_balance=account_balance- #{paymoney}  where  account_balance>#{paymoney} and account_no=#{accountNo} ")
    int substractBalance(@Param("accountNo") String accountNo, @Param("paymoney") Double paymoney);
    @Update("update account_info set account_balance=account_balance+ #{paymoney}  where account_no=#{accountNo}")
    int addBalance(@Param("accountNo") String accountNo, @Param("paymoney") Double paymoney);

    @Select("select count(1) from de_duplication where tx_no=#{txNo}")
    int isExistTx(String txNo);

    @Insert("insert into de_duplication (tx_no,create_time,account_no)values(#{txNo},now(),#{accountNo})")
    int addTx(@Param("txNo") String txNo,@Param("accountNo")String accountNo);
}
