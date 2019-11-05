package com.notice.bank1.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


@Configuration
@MapperScan(sqlSessionTemplateRef = "mysqlSqlSessionTemplate",basePackages = "com.rocketmq.bank1.dao")
public class DruidDataSourceConfiguration {
    private static final String PRE="spring.datasource.druid.mysql.";
    @Value("${"+PRE+"url}")
   private String url;
    @Value("${"+PRE+"driver-class-name}")
   private String driverClassName;
    @Value("${"+PRE+"username}")
   private String username;
    @Value("${"+PRE+"password}")
   private String password;
    @Value("${"+PRE+"initialSize}")
   private int initialSize;
    @Value("${"+PRE+"maxActive}")
   private int maxActive;
    @Value("${"+PRE+"minIdle}")
   private int minIdle;
    @Value("${"+PRE+"maxWait}")
   private long maxWait;


    @Autowired
    private Environment env;

    @Primary
    @Bean("bank1")
    public DataSource mysqlDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setMaxWait(maxWait);
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
    @Primary
    @Bean("mysqlPlatformTransactionManager")
    public PlatformTransactionManager mysqlPlatformTransactionManager(){
        return new DataSourceTransactionManager(mysqlDataSource());
    }


}
