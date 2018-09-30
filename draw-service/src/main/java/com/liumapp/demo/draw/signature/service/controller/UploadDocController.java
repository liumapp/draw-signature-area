package com.liumapp.demo.draw.signature.service.controller;

import com.liumapp.demo.draw.signature.service.entity.DocEntity;
import com.liumapp.qtools.file.base64.Base64FileTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * file UploadDocController.java
 * author liumapp
 * github https://github.com/liumapp
 * email liumapp.com@gmail.com
 * homepage http://www.liumapp.com
 * date 2018/9/28
 */
@RequestMapping("upload")
@RestController
public class UploadDocController {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Value("${liumapp.savepath}")
    private String savepath;

    @RequestMapping("/doc")
    public String uploadDoc (@RequestBody DocEntity docEntity) throws IOException {
        logger.info("ready to handle doc uploaded.");
        Base64FileTool.saveBase64File(docEntity.getContent(), savepath + "/upload/" + docEntity.getName());
        return "success";
    }

}
