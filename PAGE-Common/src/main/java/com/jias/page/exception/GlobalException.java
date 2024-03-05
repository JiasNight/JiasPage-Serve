package com.jias.page.exception;

import com.jias.page.utils.resultUtil.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** 全局统一异常处理 */
@RestControllerAdvice
public class GlobalException {

  private static final Logger logger = LoggerFactory.getLogger(GlobalException.class);

  @ExceptionHandler(Exception.class)
  public Result exceptionHandler(Exception e) {
    logger.debug("*******************全局异常*******************");
    logger.debug(e.getMessage());
    logger.debug("*******************全局异常*******************");
    return Result.failure(e.getMessage());
  }

  @ExceptionHandler(ServiceException.class)
  public Result serviceHandler(ServiceException e) {
    logger.debug("*******************业务异常*******************");
    logger.debug(e.getMessage());
    logger.debug("*******************业务异常*******************");
    return Result.failure(e.getCode(), e.getMessage());
  }
}
