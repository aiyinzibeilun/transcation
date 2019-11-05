package com.notice.bank2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class RocketMQBank2Application {

    public static void main(String[] args) {
        SpringApplication.run(RocketMQBank2Application.class, args);
    }

}
