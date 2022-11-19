package com.design.oop.c09;

public class PrivateImageStore implements ImageStore{
    @Override
    public String upload(Image image, String bucketName) {
        createBucketIfNotExisting(bucketName);
        //...上传图片到私有云...
        //...返回图片的url...
        return "上传成功";
    }

    @Override
    public Image download(String url) {
        //...从私有云下载图片...
        return new Image();
    }

    private void createBucketIfNotExisting(String bucketName) {
        // ...创建bucket...
        // ...失败会抛出异常..
    }
}
