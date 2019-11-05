package com.test.bank2.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AccountInfoDao {
    @Update("update account_info set account_balance=account_balance- #{paymoney}  where  account_balance>#{paymoney} and account_no=#{accountNo} ")
    int substractBalance(@Param("accountNo") String accountNo, @Param("paymoney") Double paymoney);
    @Update("update account_info set account_balance=account_balance+ #{paymoney}  where account_no=#{accountNo}")
    int addBalance(@Param("accountNo") String accountNo, @Param("paymoney") Double paymoney);
    /**
     * 添加分支事务try的记录
     * @param txNo
     * @return
     */
    @Insert("insert into local_try_log (tx_no,create_time)values(#{txNo},now())")
    int addTry(String txNo);
    @Insert("insert into local_confirm_log (tx_no,create_time)values(#{txNo},now()) ")
    int addConfirm(String txNo);
    @Insert("insert into local_cancel_log (tx_no,create_time)values(#{txNo},now()) ")
    int addCancel(String txNo);

    /**
     * 用来作幂等性：看数据库是否存在
     * @return
     */
    @Select("select count(1) from local_try_log where tx_no=#{txNo}")
    int isExistTry(String txNo);
    @Select("select count(1) from local_confirm_log where tx_no=#{txNo}")
    int isExistConfirm(String txNo);
    @Select("select count(1) from local_cancel_log where tx_no=#{txNo}")
    int isExistCancel(String txNo);
}
