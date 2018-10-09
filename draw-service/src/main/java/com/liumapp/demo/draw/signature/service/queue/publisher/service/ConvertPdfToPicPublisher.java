package com.liumapp.demo.draw.signature.service.queue.publisher.service;

import com.liumapp.rabbitmq.publisher.BasicPublisher;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * file ConvertPdfToPicPublisher.java
 * author liumapp
 * github https://github.com/liumapp
 * email liumapp.com@gmail.com
 * homepage http://www.liumapp.com
 * date 2018/10/9
 */
public class ConvertPdfToPicPublisher extends BasicPublisher {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

    }

    @Override
    public void setCallBack() {

    }

    @Override
    public void send(String msg) {

    }

}
