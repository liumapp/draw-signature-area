package com.liumapp.demo.draw.signature.service;

import com.liumapp.qtools.starter.springboot.QtoolsSpringBootStarter;
import com.liumapp.rabbitmq.RabbitMQOperatorMain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * file DrawServiceMain.java
 * author liumapp
 * github https://github.com/liumapp
 * email liumapp.com@gmail.com
 * homepage http://www.liumapp.com
 * date 2018/9/28
 */
@SpringBootApplication
@Import({RabbitMQOperatorMain.class, QtoolsSpringBootStarter.class})
public class DrawServiceMain {

    public static void main (String[] args) {
        SpringApplication.run(DrawServiceMain.class, args);
    }

}
