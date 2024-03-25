package com.jias.page.utils.cryptionUtil;

import com.jias.page.exception.CustomException;
import com.jias.page.exception.ServiceException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Random;

public class AESUtil {
  // 用于生成key
  public static final String ALL_CHAR =
      "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

  private static final String KEY_ALGORITHM = "AES";
  // 算法/模式/补码方式
  private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

  /**
   * AES 加密操作
   *
   * @param content 要加密的数据
   * @param key 加密key
   * @return 加密的结果
   */
  public static String encrypt(String content, String key) {
    try {
      // 获得密码的字节数组
      byte[] keyBytes = key.getBytes();
      // 根据密码生成AES密钥
      SecretKeySpec sKeySpec = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
      // 根据指定算法ALGORITHM自成密码器
      Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
      //            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);
      // 初始化密码器，第一个参数为加密(ENCRYPT_MODE)或者解密(DECRYPT_MODE)操作，第二个参数为生成的AES密钥 16位
      //            cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, ivParameterSpec);
      cipher.init(Cipher.ENCRYPT_MODE, sKeySpec);
      // 获取加密内容的字节数组(设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
      byte[] byteContent = content.getBytes("utf-8");
      // 密码器加密数据
      byte[] encodeContent = cipher.doFinal(byteContent);
      // 将加密后的数据转换为Base64编码的字符串返回
      return Base64.encodeBase64String(encodeContent);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * AES 解密操作
   *
   * @param content 解密内容
   * @param key 解密key
   * @return 解密的结果
   */
  public static String decrypt(String content, String key) {
    try {
      // 获得密码的字节数组
      byte[] keyBytes = key.getBytes();
      //            byte[] ivBytes = iv.getBytes();
      // 根据密码生成AES密钥
      SecretKeySpec sKeySpec = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
      // 根据指定算法ALGORITHM自成密码器
      Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
      //            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);
      // 初始化密码器，第一个参数为加密(ENCRYPT_MODE)或者解密(DECRYPT_MODE)操作，第二个参数为生成的AES密钥
      cipher.init(Cipher.DECRYPT_MODE, sKeySpec);
      // 把密文字符串Base64转回密文字节数组
      byte[] encodeContent = Base64.decodeBase64(content);
      // 密码器解密数据
      byte[] byteContent = cipher.doFinal(encodeContent);
      // 将解密后的数据转换为字符串返回
      return new String(byteContent, "utf-8");
    } catch (Exception e) {
      throw new CustomException("解密失败----->" + e.getMessage());
    }
  }

  /**
   * 生成一个定长的随机字符串(只包含大小写字母、数字)
   *
   * @param length 长度
   * @return 前端加解密的密钥
   */
  public static String getSecretKey(int length) {
    StringBuffer sb = new StringBuffer();
    Random random = new Random();
    for (int i = 0; i < length; i++) {
      sb.append(ALL_CHAR.charAt(random.nextInt(ALL_CHAR.length())));
    }
    return sb.toString();
  }
}
