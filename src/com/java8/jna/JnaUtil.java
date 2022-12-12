package com.java8.jna;

import com.sun.jna.Native;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JnaUtil {

    public static Logger logger = LoggerFactory.getLogger(JnaUtil.class);
    static CLibrary INSTANCE;
    static U8LoginLibrary U8_INSTANCE;

    static {
        // 获取jdk位数
        String bits = System.getProperty("sun.arch.data.model");
        // 获取os名称
        String ops = System.getProperty("os.name");
        logger.info("jdk bits=" + bits);
        logger.info("option sysetm=" + ops);
        if (ops.startsWith("win") || ops.startsWith("Win")) {
            if ("32".equals(bits)) {
                logger.info("use test_32.dll");
                INSTANCE = Native.load("test_32.dll", CLibrary.class);
            }
            if ("64".equals(bits)) {
                logger.info("use test_64.dll");
                INSTANCE = Native.load("test_64.dll", CLibrary.class);
                U8_INSTANCE = Native.load("Interop.U8Login.dll", U8LoginLibrary.class);
            }
        } else {
            if ("32".equals(bits)) {
                logger.info("use test_64-x86_32.so");
                INSTANCE = Native.load("test_64-x86_32.so", CLibrary.class);
            }
            if ("64".equals(bits)) {
                logger.info("use test_64-x86_64.so");
                INSTANCE = Native.load("test_64-x86_64.so", CLibrary.class);
            }
        }
    }

}
