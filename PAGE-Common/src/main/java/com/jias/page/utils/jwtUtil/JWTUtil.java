package com.jias.page.utils.jwtUtil;

import com.jias.page.enums.ResultEnum;
import com.jias.page.exception.CustomException;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;

@Component
public class JWTUtil implements Serializable {

  private static final Logger logger = LoggerFactory.getLogger(JWTUtil.class);

  private static final long serialVersionUID = 1L;

  /**
   * 权限列表
   */
  public static final String AUTH_HEADER_KEY = "Authorization";

  /**
   * token开始字符
   */
  public static final String TOKEN_PREFIX = "Bearer ";

  /**
   * 密钥
   */
  private static final String SECRET =
      "S/4AN9IsSRUC~{0c4]y#$F2XbV8^`#a14vawn<~Kr@(D%3TF-p1s/h{Y9k7y((rR";
  /**
   * 有效期12小时
   */
  private static final long defaultExpireTime = 1000 * 60 * 60 * 12;

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
  public static String createJwt(String id, String subject, Map<String, Object> map) {
    try{

      // 1、设置失效时间啊
      Date now = new Date();
      Date expTime = new Date(now.getTime() + defaultExpireTime);

      // 2、创建JwtBuilder
      JwtBuilder jwtBuilder = Jwts.builder();
      jwtBuilder
              .id(id) // id 这个可以不填，但是建议填
              .issuer("jias") // 签发者
              .subject(subject) // 主体
              .signWith(key) // 签名方式
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
  public static Claims parseJwt(String token) {
    System.out.println("第一：" + token);
    String str = token.replaceAll("\"", "");
    System.out.println("第二：" + str);
    try {
      // Claims claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(str).getPayload();
      JwtParserBuilder jwtParserBuilder = Jwts.parser().verifyWith(key);
      Jws<Claims> claimsJws = jwtParserBuilder.build().parseSignedClaims(str);
      Claims payload = claimsJws.getPayload();
      return payload;
    } catch (ExpiredJwtException  eje) {
      logger.error("===== Token过期 =====", eje);
      throw new CustomException(ResultEnum.PERMISSION_TOKEN_EXPIRED.getMessage());
    } catch (Exception e){
      logger.error("===== token解析异常 =====", e);
      throw new CustomException(ResultEnum.PERMISSION_TOKEN_INVALID.getMessage());
    }
  }

  /**
   * 是否已过期
   * @param token
   * @return
   */
  public static boolean isExpiration(String token) {
    Claims claims = parseJwt(token);
    System.out.println(claims);
    return parseJwt(token).getExpiration().before(new Date());
  }


}
