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

  /**
   * 业务异常
   *
   * @param e
   * @return
   */
  @ExceptionHandler(ServiceException.class)
  public Result serviceHandler(ServiceException e) {
    logger.debug("*******************业务异常*******************");
    logger.debug(e.getMessage());
    logger.debug("*******************业务异常*******************");
    return Result.failure(500, "业务异常:" + e.getMessage());
  }

  /**
   * 自定义异常
   *
   * @param e
   * @return
   */
  @ExceptionHandler(CustomException.class)
  public Result exceptionCategoryDelete(CustomException e) {
    logger.debug("*******************自定义异常*******************");
    logger.debug(e.getMessage());
    logger.debug("*******************自定义异常*******************");
    return Result.failure(500, "自定义异常:" + e.getMessage());
  }

  /**
   * 空指针异常
   *
   * @param e
   * @return
   */
  @ExceptionHandler(NullPointerException.class)
  public Result handleNullPointerException(NullPointerException e) {
    logger.debug("*******************空指针异常*******************");
    logger.debug(e.getMessage());
    logger.debug("*******************空指针异常*******************");
    return Result.failure(500, "空指针异常:" + e.getMessage());
  }

  /**
   * 处理默认异常
   *
   * @param e
   * @return
   */
  @ExceptionHandler(Exception.class)
  public Result exceptionHandler(Exception e) {
    logger.debug("*******************全局异常*******************");
    logger.debug(e.getMessage());
    logger.debug("*******************全局异常*******************");
    return Result.failure(500, "全局异常:" + e.getMessage());
  }
}
