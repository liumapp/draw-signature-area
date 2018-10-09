package com.liumapp.demo.draw.signature.service.queue.publisher.service;

import com.liumapp.rabbitmq.publisher.BasicPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("convert pdf to pic service get confirmed info : " + correlationData);
        if (ack) {
            logger.info("send msg success");
        } else {
            logger.error("send msg failed and the reason is : " + cause);
        }
    }

    @Override
    public void setCallBack() {
        rabbitTemplate.setReturnCallback(this);
        rabbitTemplate.setConfirmCallback(this);
    }

    @Override
    public void send(String msg) {
        logger.info("convert pdf to pic service ready to send msg : " + msg);
        this.sendMessage("convertPdfToPicConsumer", "process", Integer.toString(10113), msg);
    }

}
