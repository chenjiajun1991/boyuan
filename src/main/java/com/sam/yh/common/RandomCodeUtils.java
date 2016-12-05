package com.sam.yh.common;

public class RandomCodeUtils {

    public static final String KEY1 = "0123456789";
    public static final String KEY2 = "abcdefghijklmnopqrstuvwxyz";
    public static final String KEY3 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 生成6位随机数字
     */
    public static String genSmsCode() {
        return getRandStr(KEY1, 6);
    }

    public static String genInitPwd() {
        return getRandStr(KEY1, 8);
    }

    public static String genSalt() {
        return getRandStr(KEY1 + KEY2 + KEY3, 10);
    }

    public static String genBtyPubSn() {
        return getRandStr(KEY1 + KEY2 + KEY3, 8);
    }

    private static String getRandStr(String key, int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(key.charAt((int) (Math.random() * key.length())));
        }

        return sb.toString();
    }

}
