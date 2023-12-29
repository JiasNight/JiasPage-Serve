package com.jias.page.intercept;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jias.page.utils.cryptionUtil.AESUtil;
import com.jias.page.utils.redisUtil.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
@Slf4j
public class RequestIntercept implements HandlerInterceptor {

  @Autowired private RedisUtil redisUtil;

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
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
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
    //        log.info("请求地址：{}, 请求参数：{}", url, sb.toString());
    //        JSONObject jsonObject = JSON.parseObject(sb.toString());
    //        System.out.println(jsonObject.getString("aesKey"));
    //        String data = jsonObject.getString("data");
    //        String aesKey = jsonObject.getString("aesKey");
    //        String publicKey = jsonObject.getString("publicKey");
    //        String content = AESUtil.decrypt(data, redisUtil.get("aesKey").toString());
    //        System.out.println(content);
    //        System.out.println(redisUtil.get("aesKey"));
    // 从请求头里面获取token,因为每次都会在请求头里面携带token
    System.out.println(request.getRequestURI());
    String token = request.getHeader("Authorization");
    System.out.println(token);
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
