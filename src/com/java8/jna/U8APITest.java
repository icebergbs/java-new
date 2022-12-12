package com.java8.jna;

import com.sun.jna.Native;

/**
 * @author bingshan
 * @date 2022/12/9 15:00
 */
public class U8APITest {
    public static void main(String[] args) {

        final U8LoginLibrary library = (U8LoginLibrary) Native.loadLibrary("Interop.U8Login", U8LoginLibrary.class);

        library.clsLogin();


//        String sSubId = "AS";
//        String sAccID = "(default)@888";
//        String sYear = "2008";
//        String sUserID = "demo";
//        String sPassword = "";
//        String sDate = "2008-11-11";
//        String sServer = "localhost";
//        String sSerial = "";
//        Boolean bool = library.Login(sSubId, sAccID, sYear, sUserID, sPassword, sDate, sServer, sSerial);
//        System.out.println(bool);
    }
}


//
//    //第一步：构造u8login对象并登陆(引用U8API类库中的Interop.U8Login.dll)
//    //如果当前环境中有login对象则可以省去第一步
//    U8Login.clsLogin u8Login = new U8Login.clsLogin();
//    String sSubId = "AS";
//    String sAccID = "(default)@888";
//    String sYear = "2008";
//    String sUserID = "demo";
//    String sPassword = "";
//    String sDate = "2008-11-11";
//    String sServer = "localhost";
//    String sSerial = "";
//			if(!u8Login.Login(ref sSubId, ref sAccID, ref sYear, ref sUserID, ref sPassword, ref sDate, ref sServer, ref sSerial))
//
