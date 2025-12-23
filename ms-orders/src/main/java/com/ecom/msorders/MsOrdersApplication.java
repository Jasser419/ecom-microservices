package com.ecom.msorders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@ConfigurationPropertiesScan
@EnableFeignClients(basePackages = "com.ecom.msorders.client")

@SpringBootApplication
public class MsOrdersApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsOrdersApplication.class, args);
    }

}
