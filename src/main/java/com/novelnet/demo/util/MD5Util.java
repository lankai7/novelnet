package com.novelnet.demo.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String stringToMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

//    public static String getMD5Hash(String input) {
//        try {
//            // 创建MessageDigest对象
//            MessageDigest md = MessageDigest.getInstance("MD5");
//
//            // 计算MD5哈希值
//            md.update(input.getBytes());
//            byte[] byteData = md.digest();
//
//            // 转换为十六进制字符串
//            StringBuilder hexString = new StringBuilder();
//            for (byte b : byteData) {
//                String hex = Integer.toHexString(0xff & b);
//                if (hex.length() == 1) hexString.append('0');
//                hexString.append(hex);
//            }
//
//            return hexString.toString();
//
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
