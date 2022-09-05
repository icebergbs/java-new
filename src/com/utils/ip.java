package com.utils;

import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author bingshan
 * @date 2021/10/17 20:56
 */
public class ip {

    public static void main(String[] args) {

        System.out.println(isIp("255.10.1.1,10.10.1.2:8000"));

        System.out.println(isUrl("123ww中"));
    }

    public static boolean isIp(String ip) {
        /**
         * [1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5]
         *
         *  \\d  [0-9]
         *  {2}  匹配确定的n次
         *
         */
        //([1-9]|[1-9]\d|1\d{2}|2[0-4]\d|25[0-5])(\.(\d|[1-9]\d|1\d{2}|2[0-4]\d|25[0-5])){3}(:([0-9]|[1-9]\d|[1-9]\d{2}|[1-9]\d{3}|[1-5]\d{4}|6[0-4]\d{3}|65[0-4]\d{2}|655[0-2]\d|6553[0-5]))?(,([1-9]|[1-9]\d|1\d{2}|2[0-4]\d|25[0-5])(\.(\d|[1-9]\d|1\d{2}|2[0-4]\d|25[0-5])){3}(:[0-9]|[1-9]\d|[1-9]\d{2}|[1-9]\d{3}|[1-5]\d{4}|6[0-4]\d{3}|65[0-4]\d{2}|655[0-2]\d|6553[0-5])?)*
        boolean b1 = ip.matches(
                "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}" +
                        "(:([0-9]|[1-9]\\d|[1-9]\\d{2}|[1-9]\\d{3}|[1-5]\\d{4}|6[0-4]\\d{3}|65[0-4]\\d{2}|655[0-2]\\d|6553[0-5]))?" +
                        "(," +
                        "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}" +
                        "(:([0-9]|[1-9]\\d|[1-9]\\d{2}|[1-9]\\d{3}|[1-5]\\d{4}|6[0-4]\\d{3}|65[0-4]\\d{2}|655[0-2]\\d|6553[0-5]))?" +
                        ")*");
        return b1;
    }

    public static boolean isUrl(String url) {
        /**
         * 	^[^_\-\s][^\u4E00-\u9FA5]+
         */
        String urlPattern = "^[^_\\-\\s][^\\u4E00-\\u9FA5]+";

        return url.matches(urlPattern);
    }
}
