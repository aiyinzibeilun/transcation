package com.notice.bank2.dao;

import com.notice.bank2.entity.AccountPay;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AccountInfoDao {
    @Insert("insert into account_pay (id,account_no,pay_amount,result)values" +
            "(#{id},#{accountNo},#{payAmount},#{result})")
    int insertAccountPay(@Param("id")int id,@Param("accountNo")String accountNo,
                         @Param("payAmount")Double payAmount,@Param("result")String result);

    @Select("select id,account_no,pay_amount,result from account_pay where id=#{id}")
    AccountPay findByTxNo(@Param("id")int id);

}
