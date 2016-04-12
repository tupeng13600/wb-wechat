package com.tt.wb.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Tupeng on 2016/4/11.
 */
public class DecriptUtil {

    /**
     * sha1加密
     *
     * @param decript
     * @return
     */
    public static String SHA1(String decript) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        digest.update(decript.getBytes());
        byte[] messageDigest = digest.digest();
        // Create Hex String
        StringBuffer hexString = new StringBuffer();
        // 字节数组转换为 十六进制 数
        for (byte mess : messageDigest) {
            String shaHex = Integer.toHexString(mess & 0xFF);
            if (shaHex.length() < 2) {
                hexString.append(0);
            }
            hexString.append(shaHex);
        }
        return hexString.toString().toLowerCase();
    }
}
