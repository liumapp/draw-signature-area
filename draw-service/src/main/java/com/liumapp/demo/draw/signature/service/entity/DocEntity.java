package com.liumapp.demo.draw.signature.service.entity;

import org.springframework.stereotype.Component;

/**
 * file DocEntity.java
 * author liumapp
 * github https://github.com/liumapp
 * email liumapp.com@gmail.com
 * homepage http://www.liumapp.com
 * date 2018/9/28
 */
@Component
public class DocEntity {

    private String name;

    private String content;

    public DocEntity() {
    }

    public DocEntity(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public DocEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getContent() {
        return content;
    }

    public DocEntity setContent(String content) {
        this.content = content;
        return this;
    }
}
