package com.jias.page.security;

import com.jias.page.utils.md5Util.MD5Util;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author JSON
 * @date 2024/4/25
 * @description
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return MD5Util.md5((String) rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equalsIgnoreCase(MD5Util.md5((String) rawPassword));
    }
}
