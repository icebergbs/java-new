package com.java8.jna;

import com.sun.jna.Library;

/**
 * @Description TODO
 * @Date 2021/7/7 9:07
 */
public interface U8LoginLibrary extends Library {
    U8LoginLibrary U8_INSTANCE =  JnaUtil.U8_INSTANCE;

     clsLogin clsLogin();

}

