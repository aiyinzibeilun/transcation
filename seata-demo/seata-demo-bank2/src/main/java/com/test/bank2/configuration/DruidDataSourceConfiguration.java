package com.test.bank2.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


@Configuration
@MapperScan(sqlSessionTemplateRef = "mysqlSqlSessionTemplate",basePackages = "com.test.bank2.dao")
public class DruidDataSourceConfiguration {

    @Bean("bank2")
    @ConfigurationProperties("spring.datasource.druid.mysql")
    public DruidDataSource mysqlDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
       return druidDataSource;
    }
    @Bean
    @Primary
    public DataSource dataSource(DruidDataSource druidDataSource){
        //使用seata代理数据源
        DataSourceProxy dataSourceProxy = new DataSourceProxy(druidDataSource);
        return dataSourceProxy;
    }
    @Primary
    @Bean("mysqlSqlSessionFactory")
    public SqlSessionFactory mysqlSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(mysqlDataSource());
        return sqlSessionFactoryBean.getObject();
    }
    @Primary
    @Bean("mysqlSqlSessionTemplate")
    public SqlSessionTemplate mysqlSqlSessionTemplate()throws Exception{
        return new SqlSessionTemplate(mysqlSqlSessionFactory());
    }


}
