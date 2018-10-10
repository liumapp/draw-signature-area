package com.liumapp.demo.draw.signature.service.queue.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liumapp.demo.draw.signature.service.config.ConvertConfig;
import com.liumapp.demo.draw.signature.service.queue.pattern.ConvertDocPattern;
import com.liumapp.demo.draw.signature.service.queue.pattern.ConvertPdfToPicPattern;
import com.liumapp.demo.draw.signature.service.queue.pattern.QueueJobErrorInfoPattern;
import com.liumapp.demo.draw.signature.service.socket.ConvertingResultSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author liumapp
 * @file ConvertDocResultService.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/16/18
 */
@Service
public class QueueJobErrorInfoConsumer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void handleError(String jsonObject) {
        QueueJobErrorInfoPattern queueJobErrorInfoPattern = JSON.parseObject(jsonObject, QueueJobErrorInfoPattern.class);
        if (queueJobErrorInfoPattern.getServiceName().equals(ConverterConsumer.class.toString())) {
            this.handleConvertError(queueJobErrorInfoPattern.getInfo());
        } else if (queueJobErrorInfoPattern.getServiceName().equals(ConvertPdfToPicConsumer.class.toString())) {
            this.handleConvertPdfToPicError(queueJobErrorInfoPattern.getInfo());
        } else {
            logger.error("can not find error service");
        }
    }

    private void handleConvertPdfToPicError (String info) {
        ConvertPdfToPicPattern pdfToPicPattern = JSON.parseObject(info, ConvertPdfToPicPattern.class);
        ConvertingResultSocketServer.sendStatusMessage(responseConvertJson(pdfToPicPattern), pdfToPicPattern.getConvertId());
    }

    private void handleConvertError (String info) {
        ConvertDocPattern docPattern = JSON.parseObject(info, ConvertDocPattern.class);
        ConvertingResultSocketServer.sendStatusMessage(responseConvertJson(docPattern), docPattern.getConvertId());
    }

    private JSONObject responseConvertJson (ConvertDocPattern docPattern) {
        JSONObject object = new JSONObject();
        object.put("index", docPattern.getFileIndex());
        object.put("savename", docPattern.getSaveName());
        object.put("status", ConvertConfig.ConvertStatus.CONVERTED_FAILD);
        return object;
    }

}
