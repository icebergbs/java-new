package com.design.oop.c09;

/**
 * 基于接口而非实现编程
 * 如何将这条原则应用到实战中？
 */
// ImageStore的使用举例
public class ImageProcessingJob {

    private static final String BUCKET_NAME = "ai_images_bucket";
    //...省略其他无关代码...
    public void process() {
        Image image = new Image();//处理图片，并封装为Image对象
        ImageStore imageStore = new PrivateImageStore();
        imageStore.upload(image, BUCKET_NAME);
    }
}
