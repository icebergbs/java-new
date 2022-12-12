package com.java8.jna;

import com.sun.jna.Library;

/**
 * @Description TODO
 * @Date 2021/7/7 9:07
 */
public interface clsLogin extends Library {
    boolean Login(String sSubId,
                  String sAccID,
                  String sYear,
                  String sUserID,
                  String sPassword,
                  String sDate,
                  String sServer,
                  String sSerial);



}

