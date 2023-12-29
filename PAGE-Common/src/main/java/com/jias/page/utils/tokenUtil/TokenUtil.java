package com.jias.page.utils.tokenUtil;

import com.jias.page.utils.redisUtil.RedisUtil;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class TokenUtil {

  // 生成token(格式为token:设备-加密的用户名-时间-六位随机数)
  public String generateToken(String username) {
    StringBuilder token = new StringBuilder("token:");
    // 设备
    //    UserAgent userAgent = UserAgent.parseUserAgentString(userAgentStr);
    //    if (userAgent.getOperatingSystem().isMobileDevice()) {
    //      token.append("MOBILE-");
    //    } else {
    //      token.append("PC-");
    //    }
    // 加密的用户名
    token.append("XyZFT6uaDE8yhYvvMzpSkzFWlml9B6As");
    // 时间
    token.append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "-");
    // 六位随机字符串
    token.append(new Random().nextInt(999999 - 111111 + 1) + 111111);
    System.out.println("token-->" + token.toString());

    return token.toString();
  }
}
