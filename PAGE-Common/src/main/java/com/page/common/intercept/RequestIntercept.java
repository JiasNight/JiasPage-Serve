package com.page.common.intercept;

import com.page.common.configuration.JwtConfiguration;
import com.page.common.utils.redisUtil.RedisUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class RequestIntercept implements HandlerInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(RequestIntercept.class);

  @Autowired private RedisUtil redisUtil;

  @Autowired
  private JwtConfiguration jwtConfiguration;


  /**
   * //三个方法的运行顺序为 preHandle -> postHandle -> afterCompletion //如果preHandle返回值为false，三个方法仅运行preHandle
   * 前置处理方法：原始方法之前执行
   *
   * @param request
   * @param response
   * @param handler
   * @return
   * @throws Exception
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//    // 忽略带JwtIgnore注解的请求, 不做后续token认证校验
//    if (handler instanceof HandlerMethod) {
//      HandlerMethod handlerMethod = (HandlerMethod) handler;
//      JwtIgnore jwtIgnore = handlerMethod.getMethodAnnotation(JwtIgnore.class);
//      if (jwtIgnore != null) {
//        return true;
//      }
//    }
//
//    if (HttpMethod.OPTIONS.equals(request.getMethod())) {
//      response.setStatus(HttpServletResponse.SC_OK);
//      return true;
//    }
//
//    // 获取请求头信息authorization信息
//    final String authHeader = request.getHeader(JwtUtil.AUTH_HEADER_KEY);
//    logger.info("## authHeader= {}", authHeader);
//
//    if (StringUtils.isBlank(authHeader) || !authHeader.startsWith(JwtUtil.TOKEN_PREFIX)) {
//      logger.info("### 用户未登录，请先登录 ###");
//      throw new CustomException(ResultEnum.USER_NOT_LOGIN.getMessage());
//    }
//
//    // 获取token
//    final String token = authHeader.substring(7);
//
//    if(audience == null){
//      BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
//      audience = (Audience) factory.getBean("audience");
//    }
//
//    // 验证token是否有效--无效已做异常抛出，由全局异常处理后返回对应信息
//    JwtUtil.parseJwt(token);

    //        log.debug(request.getMethod());
    //        log.debug(request.getRequestURI());
    //        StringBuffer url = request.getRequestURL();
    //        BufferedReader br = null;
    //        try {
    //            br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
    //        } catch (IOException e) {
    //            e.printStackTrace();
    //        }
    //        String line = null;
    //        StringBuilder sb = new StringBuilder();
    //        try {
    //            while ((line = br.readLine()) != null) {
    //                sb.append(line);
    //            }
    //        } catch (IOException e) {
    //            e.printStackTrace();
    //        }
    ////        log.info("请求地址：{}, 请求参数：{}", url, sb.toString());
    //        JSONObject jsonObject = JSON.parseObject(sb.toString());
    //        System.out.println(jsonObject.getString("aesKey"));
    //        String data = jsonObject.getString("data");
    //        String aesKey = jsonObject.getString("aesKey");
    //        String publicKey = jsonObject.getString("publicKey");
    //        String content = AESUtil.decrypt(data, redisUtil.get("aesKey").toString());
    //        System.out.println(content);
    return true;
  }

  /**
   * 后置处理方法：原始方法运行后运行，如果原始方法被拦截，则不执行
   *
   * @param request
   * @param response
   * @param handler
   * @return
   * @throws Exception
   */
  @Override
  public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      ModelAndView modelAndView)
      throws Exception {
    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
  }

  /**
   * 完成处理方法：拦截器最后执行的方法，无论原始方法是否执行
   *
   * @param request
   * @param response
   * @param handler
   * @return
   * @throws Exception
   */
  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
    HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
  }
}
