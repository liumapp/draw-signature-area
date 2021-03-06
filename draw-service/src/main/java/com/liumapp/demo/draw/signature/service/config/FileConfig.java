package com.liumapp.demo.draw.signature.service.config;

import com.liumapp.qtools.starter.springboot.file.FileManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liumapp
 * @file FileConfig.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/3/18
 */
@Configuration
public class FileConfig {

    @Bean
    @ConfigurationProperties(prefix = "liumapp.filemanager")
    public FileManager fileManager () {
        return new FileManager();
    }

}
