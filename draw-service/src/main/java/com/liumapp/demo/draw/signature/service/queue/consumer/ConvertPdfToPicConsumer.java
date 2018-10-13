package com.liumapp.demo.draw.signature.service.queue.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liumapp.convert.img.service.AllPageConverter;
import com.liumapp.demo.draw.signature.service.config.ConvertConfig;
import com.liumapp.demo.draw.signature.service.queue.pattern.ConvertDocPattern;
import com.liumapp.demo.draw.signature.service.queue.pattern.ConvertPdfToPicPattern;
import com.liumapp.demo.draw.signature.service.queue.pattern.QueueJobErrorInfoPattern;
import com.liumapp.demo.draw.signature.service.queue.publisher.service.QueueJobErrorInfoPublisher;
import com.liumapp.demo.draw.signature.service.socket.ConvertingResultSocketServer;
import com.liumapp.qtools.starter.springboot.file.FileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * file ConvertPdfToPicConsumer.java
 * author liumapp
 * github https://github.com/liumapp
 * email liumapp.com@gmail.com
 * homepage http://www.liumapp.com
 * date 2018/10/9
 */
@Service
public class ConvertPdfToPicConsumer {

    @Autowired
    private FileManager fileManager;

    @Autowired
    private QueueJobErrorInfoPattern queueJobErrorInfoPattern;

    @Autowired
    private QueueJobErrorInfoPublisher queueJobErrorInfoPublisher;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void process (String jsonPattern) {
        ConvertPdfToPicPattern convertPdfToPicPattern = JSON.parseObject(jsonPattern, ConvertPdfToPicPattern.class);
        logger.info("convert job begin , pdf path is : " + convertPdfToPicPattern.getPdfPath());
        AllPageConverter allPageConverter = new AllPageConverter();
        try {
            allPageConverter.setSourcePdfPath(convertPdfToPicPattern.getPdfPath());
            allPageConverter.setOutputPath(fileManager.getSavePath() + "/../pic/");
            allPageConverter.convert();
            convertPdfToPicPattern.setPicNames(allPageConverter.getSavenames());
            convertPdfToPicPattern.setPicNumbers(allPageConverter.getSavenames().size());
            ConvertingResultSocketServer.sendStatusMessage(responseJson(convertPdfToPicPattern), convertPdfToPicPattern.getConvertId());
        } catch (Exception e) {
            //转换失败
            logger.error(e.getMessage());
            queueJobErrorInfoPattern.setServiceName(ConverterConsumer.class.toString());
            queueJobErrorInfoPattern.setErrorDesc("handle pic convert failed!");
            queueJobErrorInfoPattern.setInfo(jsonPattern);
            queueJobErrorInfoPublisher.send(JSON.toJSONString(queueJobErrorInfoPattern));
        }
    }

    private JSONObject responseJson (ConvertPdfToPicPattern convertPdfToPicPattern) {
        JSONObject object = new JSONObject();
        object.put("picNames", convertPdfToPicPattern.getSaveName());
        object.put("picNumbers", convertPdfToPicPattern.getPicNumbers());
        object.put("index", convertPdfToPicPattern.getFileIndex());
        object.put("savename", convertPdfToPicPattern.getSaveName());
        object.put("status", ConvertConfig.ConvertStatus.CONVERTED_SUCCESS);
        return object;
    }

}
