package com.liumapp.demo.draw.signature.service.controller;

import com.liumapp.demo.draw.signature.service.entity.DocEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/doc")
    public String uploadDoc (@RequestBody DocEntity docEntity) {
        return "success";
    }

}
