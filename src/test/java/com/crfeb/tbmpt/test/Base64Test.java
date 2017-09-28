package com.crfeb.tbmpt.test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;

import org.springframework.util.Base64Utils;

import com.crfeb.tbmpt.commons.utils.Charsets;

public class Base64Test {

    /**
     * Shiro 记住密码采用的是AES加密，AES key length 需要是16位，该方法生成16位的key
     */
    public static void main(String[] args) {
        String keyStr = "smxg";
        
        byte[] keys = keyStr.getBytes(Charsets.UTF_8);
        
        System.out.println(Base64Utils.encodeToString(Arrays.copyOf(keys, 16)));
        
        
        float scale = 34.1f; 
        DecimalFormat fnum = new DecimalFormat("##0.00000"); 
        String dd=fnum.format(scale); 
        System.out.println(Float.parseFloat(dd));
    }

}
