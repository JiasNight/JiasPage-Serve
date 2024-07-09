package com.page.common.utils;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author JSON
 * @date 2024/7/2
 * @description
 */
public class JasyptUtil {

  public static void main(String[] args) {
    StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
    /*配置文件中配置如下的算法*/
    standardPBEStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
    /*配置文件中配置的password*/
    standardPBEStringEncryptor.setPassword("JiasPage");
    // 加密
    String jasyptPasswordEN = standardPBEStringEncryptor.encrypt("root");
    // 解密
    String jasyptPasswordDE = standardPBEStringEncryptor.decrypt(jasyptPasswordEN);
    System.out.println("加密后密码：" + jasyptPasswordEN);
    System.out.println("解密后密码：" + jasyptPasswordDE);
  }
}
