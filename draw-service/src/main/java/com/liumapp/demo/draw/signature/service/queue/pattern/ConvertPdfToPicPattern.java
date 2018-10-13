package com.liumapp.demo.draw.signature.service.queue.pattern;

import org.springframework.stereotype.Component;

import java.util.LinkedList;

/**
 * file ConvertPdfToPicPattern.java
 * author liumapp
 * github https://github.com/liumapp
 * email liumapp.com@gmail.com
 * homepage http://www.liumapp.com
 * date 2018/10/9
 */
@Component
public class ConvertPdfToPicPattern extends ConvertDocPattern {

    private LinkedList<String> picNames;

    private int picNumbers;

    public ConvertPdfToPicPattern() {
    }

    public ConvertPdfToPicPattern(Integer fileIndex, Integer convertId, String docPath, String originalName, String pdfPath, String saveName, LinkedList<String> picNames, int picNumbers) {
        super(fileIndex, convertId, docPath, originalName, pdfPath, saveName);
        this.picNames = picNames;
        this.picNumbers = picNumbers;
    }

    public LinkedList<String> getPicNames() {
        return picNames;
    }

    public ConvertPdfToPicPattern setPicNames(LinkedList<String> picNames) {
        this.picNames = picNames;
        return this;
    }

    public int getPicNumbers() {
        return picNumbers;
    }

    public ConvertPdfToPicPattern setPicNumbers(int picNumbers) {
        this.picNumbers = picNumbers;
        return this;
    }
}
