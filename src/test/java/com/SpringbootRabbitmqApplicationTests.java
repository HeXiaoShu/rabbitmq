package com;

import com.servier.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootRabbitmqApplicationTests {

    @Resource
    OrderService orderServer;

    @Test
    void contextLoads() {
        orderServer.ttlMakerOrderDirect("小千","2",10);
    }

}
