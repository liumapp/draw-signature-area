package com.liumapp.demo.draw.signature.service.queue.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liumapp.convert.doc.Doc2PDF;
import com.liumapp.demo.draw.signature.service.config.ConvertConfig;
import com.liumapp.demo.draw.signature.service.queue.pattern.ConvertDocPattern;
import com.liumapp.demo.draw.signature.service.queue.pattern.ConvertPdfToPicPattern;
import com.liumapp.demo.draw.signature.service.queue.pattern.QueueJobErrorInfoPattern;
import com.liumapp.demo.draw.signature.service.queue.publisher.service.ConvertPdfToPicPublisher;
import com.liumapp.demo.draw.signature.service.queue.publisher.service.QueueJobErrorInfoPublisher;
import com.liumapp.demo.draw.signature.service.socket.ConvertingResultSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liumapp
 * @file ConverterHandler.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/6/18
 */
@Service
public class ConverterConsumer {

    @Autowired
    private Doc2PDF doc2PDF;

    @Autowired
    private QueueJobErrorInfoPublisher queueJobErrorInfoPublisher;

    @Autowired
    private QueueJobErrorInfoPattern queueJobErrorInfoPattern;

    @Autowired
    private ConvertPdfToPicPublisher convertPdfToPicPublisher;

    private static Logger logger = LoggerFactory.getLogger(ConverterConsumer.class);

    public void process (String jsonPattern) {
        ConvertDocPattern docPattern = JSON.parseObject(jsonPattern, ConvertDocPattern.class);
        logger.info("convert job begin , doc path is : " + docPattern.getDocPath());
        try {
            doc2PDF.doc2pdf(docPattern.getPdfPath() + "/" + docPattern.getSaveName(), docPattern.getDocPath() + "/" + docPattern.getOriginalName());
            convertPdfToPicPublisher.send(JSON.toJSONString(docPattern));
        } catch (Exception e) {
            logger.info(e.getMessage());
            // send msg to convert doc result that convert failed.
            queueJobErrorInfoPattern.setServiceName(ConverterConsumer.class.toString());
            queueJobErrorInfoPattern.setErrorDesc("handle doc convert failed!");
            queueJobErrorInfoPattern.setInfo(jsonPattern);
            queueJobErrorInfoPublisher.send(JSON.toJSONString(queueJobErrorInfoPattern));
        }
    }

}

