package com.page.common.utils.jwtUtil;

import com.page.common.configuration.JwtConfiguration;
import com.page.common.enums.ResultEnum;
import com.page.common.exception.CustomException;
import io.jsonwebtoken.*;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil implements Serializable {

  private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

  private static final long serialVersionUID = 1L;

  @Resource
  JwtConfiguration jwtConfiguration;

  private String secret = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY=";

  private long defaultExpiresTime = 60 * 60 * 1000;

  SecretKey secretKey =
      Jwts.SIG
          .HS256
          .key()
          .random(new SecureRandom(secret.getBytes(StandardCharsets.UTF_8)))
          .build();

  public JwtUtil() {
    System.out.println("JwtProperties is null? " + (jwtConfiguration == null));
  }

  /**
   * 生成token
   *
   * @param id 用户登录ID
   * @param subject 用户登录名
   * @param map 其他私有数据
   * @return
   */
  public String createJwt(String id, String subject, Map<String, Object> map) {
    try {
      Long getExpiresTime = jwtConfiguration.getExpiresTime();
      if (getExpiresTime != null) {
        defaultExpiresTime = getExpiresTime;
      }
      System.out.println("token最终过期时间为：" + defaultExpiresTime);

      // 1、设置失效时间啊
      Date now = new Date();
      Date expTime = new Date(now.getTime() + defaultExpiresTime);

      // 2、创建JwtBuilder
      JwtBuilder jwtBuilder = Jwts.builder();
      jwtBuilder
          .id(id) // id 这个可以不填，但是建议填
          .issuer("jias") // 签发者
          .subject(subject) // 主体
          .signWith(secretKey) // 签名方式
          .issuedAt(new Date()) // 签发时间
          .expiration(expTime); // 过期时间

      // 3、根据map设置claims
      for (Map.Entry<String, Object> entry : map.entrySet()) {
        jwtBuilder.claim(entry.getKey(), entry.getValue());
      }

      // 4、创建token
      String token = jwtBuilder.compact();

      return token;
    } catch (Exception e) {
      logger.error("签名失败", e);
      throw new CustomException(ResultEnum.PERMISSION_SIGNATURE_ERROR.getMessage());
    }
  }

  /**
   * 解析token
   *
   * @param token
   * @return
   */
  public Claims parseJwt(String token) {
    // String str = token.replaceAll("\"", "");
    try {
      // Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(str).getPayload();
      JwtParserBuilder jwtParserBuilder = Jwts.parser().verifyWith(secretKey);
      Jws<Claims> claimsJws = jwtParserBuilder.build().parseSignedClaims(token);
      Claims payload = claimsJws.getPayload();

      return payload;
    } catch (ExpiredJwtException e) {
      logger.error("===== Token过期 =====", e);
      throw new CustomException(401, ResultEnum.PERMISSION_TOKEN_EXPIRED.getMessage());
    } catch (Exception e) {
      logger.error("===== token解析异常 =====", e);
      throw new CustomException(
          ResultEnum.PERMISSION_TOKEN_INVALID.getCode(),
          ResultEnum.PERMISSION_TOKEN_INVALID.getMessage());
    }
  }

  /**
   * 是否已过期
   *
   * @param token
   * @return
   */
  public boolean isExpiration(String token) {
    return parseJwt(token).getExpiration().before(new Date());
  }
}
