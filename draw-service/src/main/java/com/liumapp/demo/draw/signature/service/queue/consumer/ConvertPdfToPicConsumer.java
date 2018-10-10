package com.liumapp.demo.draw.signature.service.queue.consumer;

import com.alibaba.fastjson.JSON;
import com.liumapp.convert.img.service.AllPageConverter;
import com.liumapp.demo.draw.signature.service.queue.pattern.ConvertDocPattern;
import com.liumapp.demo.draw.signature.service.queue.pattern.ConvertPdfToPicPattern;
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

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void process (String jsonPattern) {
        ConvertPdfToPicPattern convertPdfToPicPattern = JSON.parseObject(jsonPattern, ConvertPdfToPicPattern.class);
        logger.info("convert job begin , pdf path is : " + convertPdfToPicPattern.getPdfPath());
        AllPageConverter allPageConverter = new AllPageConverter();
        try {
            allPageConverter.setSourcePdfPath(convertPdfToPicPattern.getPdfPath());
            allPageConverter.setOutputPath(fileManager.getSavePath() + "/pic/");
            allPageConverter.convert();
            convertPdfToPicPattern.setPicNames(allPageConverter.getSavenames());
            
        } catch (Exception e) {
            //转换失败
            e.printStackTrace();
        }
    }

}
