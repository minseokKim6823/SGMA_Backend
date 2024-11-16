package com.springcooler.sgma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SgmaGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SgmaGatewayApplication.class, args);
    }

}
