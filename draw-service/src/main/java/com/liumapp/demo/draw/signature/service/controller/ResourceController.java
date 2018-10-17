package com.liumapp.demo.draw.signature.service.controller;

import com.liumapp.qtools.starter.springboot.file.FileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * file ResourceController.java
 * author liumapp
 * github https://github.com/liumapp
 * email liumapp.com@gmail.com
 * homepage http://www.liumapp.com
 * date 2018/10/16
 */
@RestController
@RequestMapping("resource")
public class ResourceController {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private FileManager fileManager;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * if set produces="image/*" , firefox can not resolve pic , and will get a 406 response error
     */
    @RequestMapping(value = "/getPic", produces="*")
    @ResponseBody
    public ResponseEntity<?> getImg (@RequestParam String filename) {
        String path = fileManager.getSavePath() + "/../pic/" + filename;
        logger.info("request file : " + path);
        return ResponseEntity.ok(resourceLoader.getResource("file:" + path));
    }

}
