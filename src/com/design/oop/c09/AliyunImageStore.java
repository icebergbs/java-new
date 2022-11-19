package com.design.oop.c09;

public class AliyunImageStore implements ImageStore{
    @Override
    public String upload(Image image, String bucketName) {
        createBucketIfNotExisting(bucketName);
        String accessToken = generateAccessToken();
        //...上传图片到阿里云...
        // ...返回图片在阿里云上的地址(url)...
        return "上传成功";
    }

    @Override
    public Image download(String url) {
        String accessToken = generateAccessToken(); //...从阿里云下载图片...
        return new Image();
    }

    private void createBucketIfNotExisting(String bucketName) {
        // ...创建bucket...
        // ...失败会抛出异常..
    }

    private String generateAccessToken() {
        // ...根据accesskey/secrectkey等生成access token
        return "token----";
    }
}
