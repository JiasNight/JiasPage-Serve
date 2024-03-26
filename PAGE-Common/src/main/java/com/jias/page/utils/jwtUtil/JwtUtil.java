package com.jias.page.utils.jwtUtil;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

  private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
  private static final String SECRET =
      "S/4AN9IsSRUC~{0c4]y#$F2XbV8^`#a14vawn<~Kr@(D%3TF-p1s/h{Y9k7y((rR";
  private static final long defaultExpire = 1000 * 60 * 60 * 24 * 7L; // 7天
  // 创建一个jwt密钥 加密和解密都需要用这个玩意
  private static final SecretKey key =
      Jwts.SIG
          .HS256
          .key()
          .random(new SecureRandom(SECRET.getBytes(StandardCharsets.UTF_8)))
          .build();

  /**
   * 设置认证token
   *
   * @param id 用户登录ID
   * @param subject 用户登录名
   * @param map 其他私有数据
   * @return
   */
  public String createJwt(String id, String subject, Map<String, Object> map, Long expire) {

    // 1、设置失效时间啊
    Date now = new Date();
    Date expTime = new Date(now.getTime() + expire);

    // 2、创建JwtBuilder
    JwtBuilder jwtBuilder = Jwts.builder();
    jwtBuilder
        .id(id) // id 这个可以不填，但是建议填
        .issuer("JIAS") // 签发者
        .subject(subject) // 主题
        .issuedAt(new Date()) // 签发时间
        .expiration(expTime) // 过期时间
        .signWith(key); // 签名方式

    // 3、根据map设置claims
    for (Map.Entry<String, Object> entry : map.entrySet()) {
      jwtBuilder.claim(entry.getKey(), entry.getValue());
    }

    jwtBuilder.header().add("JWT", "JSpWdhuPGblNZApVclmX");
    // 4、创建token
    String token = jwtBuilder.compact();
    return token;
  }

  /**
   * 解析token
   *
   * @param token
   * @return
   */
  public Claims parseJwt(String token) {
    try {
      Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
      return claims;
    } catch (Exception e) {
      if (e instanceof ExpiredJwtException) {
        // 现在不需要使用 claims.getExpiration().before(new Date());
        // 判断JWT是否过期了 如果过期会抛出ExpiredJwtException异常
        logger.error("token已过期" + e);
      }
      if (e instanceof JwtException) {
        logger.error("token已失效" + e);
      }
      logger.error("jwt解析失败" + e);
      return null;
    }
  }
}
