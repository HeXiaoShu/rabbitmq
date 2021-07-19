package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.mapper")
@SpringBootApplication
public class RabbitmqSoncumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqSoncumerApplication.class, args);
    }

}
