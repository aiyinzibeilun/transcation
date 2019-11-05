package com.notice.bank2.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;


@Configuration
@MapperScan(sqlSessionTemplateRef = "mysqlSqlSessionTemplate",basePackages = "com.rocketmq.bank2.dao")
public class DruidDataSourceConfiguration {

    @Autowired
    private Environment env;


    @Bean("bank2")
    @ConfigurationProperties("spring.datasource.druid.mysql")
    public DruidDataSource mysqlDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
       return druidDataSource;
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
