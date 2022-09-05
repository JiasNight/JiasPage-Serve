package com.jias.page.exception;

import com.jias.page.utils.resultUtil.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局统一异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        log.error("*******************全局异常*******************");
        log.error(e.getMessage());
        log.error("*******************全局异常*******************");
        return Result.failure(e.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    public Result serviceHandler(ServiceException e) {
        log.error("*******************业务异常*******************");
        log.error(e.getMessage());
        log.error("*******************业务异常*******************");
        return Result.failure(e.getCode(), e.getMessage());
    }
}
