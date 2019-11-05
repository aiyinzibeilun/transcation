package com.test.bank2;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.test"},exclude = MongoAutoConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.test"})
@EnableCircuitBreaker
@ComponentScan(basePackages = {"com.test.bank2","org.dromara.hmily"})
public class HmilyBank2Application {

    public static void main(String[] args) {
        SpringApplication.run(HmilyBank2Application.class, args);
    }

}
