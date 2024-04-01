package com.jias.page.utils.cryptionUtil;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAUtil {

    /**
     * 安全加固 PHP填充方式
     */
    private static final String CIPHER_ALGORITHM = "RSA/ECB/OAEPWITHSHA-1ANDMGF1PADDING";

    /**
     * 用于封装随机产生的公钥与私钥
     */
    private static Map<String, String> keyMap = new HashMap<String, String>();

    /**
     * 表示公钥
     */
    private static final String publicKeyFlag = "publicKey";

    /**
     * 表示私钥
     */
    private static final String privateKeyFlag = "privateKey";

    /**
     * 算法
     */
    private final static String algorithm = "RSA";

    /**
     * 密钥大小为96-1024位
     */
    private final static Integer keySize = 1024;

    /**
     * 随机生成密钥对
     * @throws NoSuchAlgorithmException
     */
    public static Map<String, String> genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(algorithm);
        // 初始化密钥对生成器
        keyPairGen.initialize(keySize,new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // 将公钥和私钥保存到Map
        keyMap.put(publicKeyFlag,publicKeyString);
        keyMap.put(privateKeyFlag,privateKeyString);
        return keyMap;
    }
    /**
     * RSA公钥加密
     * @param str 加密字符串
     * @param publicKey 公钥
     * @param urlSafe url安全
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt( String str, String publicKey, boolean urlSafe ) throws Exception{
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(algorithm).generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] bdata=cipher.doFinal(str.getBytes("UTF-8"));
        if(urlSafe) {
            return  Base64.encodeBase64URLSafeString(bdata);
        }
        return Base64.encodeBase64String(bdata);
    }

    /**
     * RSA私钥解密
     * @param str 加密字符串
     * @param privateKey 私钥
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws Exception{
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance(algorithm).generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(inputByte));
    }


    /**
     * RSA公钥加密(RSA/ECB/OAEPWITHSHA-1ANDMGF1PADDING)
     *
     * @param str
     *            加密字符串
     * @param publicKey
     *            公钥
     * @return 密文
     * @throws Exception
     *             加密过程中的异常信息
     */
    public static String encryptNew( String str, String publicKey) throws Exception{

        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(algorithm).generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] byteData=cipher.doFinal(str.getBytes("UTF-8"));

        return  Base64.encodeBase64String(byteData);
    }

    /**
     * RSA私钥解密(RSA/ECB/OAEPWITHSHA-1ANDMGF1PADDING)
     *
     * @param str
     *            加密字符串
     * @param privateKey
     *            私钥
     * @return 铭文
     * @throws Exception
     *             解密过程中的异常信息
     */
    public static String decryptNew(String str, String privateKey) throws Exception{
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance(algorithm).generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(inputByte));
    }
}
