package com.jias.page.security;

import com.jias.page.config.TransferConfig;
import com.jias.page.utils.cryptionUtil.AESUtil;
import com.jias.page.utils.md5Util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author JSON
 * @date 2024/4/25
 * @description
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    TransferConfig transferConfig;

    /**
     * 对原始密码进行编码
     * @param rawPassword
     * @return
     */
    @Override
    public String encode(CharSequence rawPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String userPassword = MD5Util.md5((String) rawPassword);
        String encodePassword = bCryptPasswordEncoder.encode(userPassword);
        return encodePassword;
    }

    /**
     * 验证从存储（比如数据库或者内存等）中获取的编码密码是否与需要验证的密码匹配
     * 如果密码匹配，则返回 true，否则返回 false
     * 存储的编码密码永远不会被解码
     * 因此会将需要验证的密码进行编码，然后与编码密码进行匹配
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = rawPassword.toString();
        // 通过AES解密得到原始密码md5加密的字符串
        String MD5Password = AESUtil.decrypt(password, transferConfig.getAesKey());
        // 再通过security加密得到加密的密码字符串
        // String resultPassword = bCryptPasswordEncoder.encode(MD5Password);
        boolean matches = bCryptPasswordEncoder.matches(MD5Password, encodedPassword);
        logger.debug("校验时待加密的密码：" + MD5Password);
        logger.debug("校验时已加密的密码：" + encodedPassword);
        return matches;
    }
}
