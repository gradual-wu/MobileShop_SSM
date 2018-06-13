package cn.wycclub.util;

import java.security.MessageDigest;
import java.util.Base64;

/**
 * 业务逻辑层工具类
 *
 * @author WuYuchen
 * @create 2018-02-13 22:06
 **/

public class ContextUtils {

    public static String textToMd5(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte md5[] = md.digest(text.getBytes());
            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(md5);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
