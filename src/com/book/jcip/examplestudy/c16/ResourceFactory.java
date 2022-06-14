package com.book.jcip.examplestudy.c16;

import com.book.jcip.annotations.ThreadSafe;

//延长初始化站位类模式
@ThreadSafe
public class ResourceFactory {
    private static class ResourceHolder {
        public static Resource resource = new Resource();
    }

    //第一次调用时,会使ResourceHolder被加载和被初始化,此时静态初始化器将执行Resource的初始化操作
    public static Resource getResource() {
        return ResourceHolder.resource;
    }

    static class Resource {
    }
}
