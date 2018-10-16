package com.liumapp.demo.draw.signature.service.controller;

import com.liumapp.qtools.starter.springboot.file.FileManager;
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

    @RequestMapping(value = "/getPic", produces="image/*")
    @ResponseBody
    public ResponseEntity<?> getImg (@RequestParam String filename) {
        return ResponseEntity.ok(resourceLoader.getResource("file:" + fileManager.getSavePath() + "/../pic/" + filename));
    }

}
