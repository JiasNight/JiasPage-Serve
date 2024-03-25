package com.jias.page.utils.resultUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Result {

  // 是否成功
  private Boolean success;

  // 响应码
  private Integer code;

  // 响应信息
  private String message;

  // 响应时间
  private String timestamp = this.timeFormat();

  // 响应数据
  private Object data;

  // 构造器私有
  private Result() {}

  public Result(Boolean success, ResultEnum resultEnum, String timestamp) {
    this.success = success;
    this.code = resultEnum.getCode();
    this.message = resultEnum.getMessage();
    this.timestamp = timestamp;
  }

  // 返回成功
  public static Result success() {
    return new Result(true, ResultEnum.SUCCESS, timeFormat());
  }

  // 返回成功
  public static Result success(ResultEnum resultEnum) {
    Result result = new Result();
    result.setSuccess(false);
    result.setResultEnum(resultEnum);
    result.setTimestamp(timeFormat());
    return result;
  }

  // 返回成功
  public static Result success(Object data) {
    Result result = new Result();
    result.setSuccess(true);
    result.setResultEnum(ResultEnum.SUCCESS);
    result.setData(data);
    result.setTimestamp(timeFormat());
    return result;
  }

  // 返回成功
  public static Result success(ResultEnum resultEnum, Object data) {
    Result result = new Result();
    result.setSuccess(true);
    result.setResultEnum(resultEnum);
    result.setData(data);
    result.setTimestamp(timeFormat());
    return result;
  }

  // 返回失败
  public static Result failure() {
    return new Result(false, ResultEnum.FAILURE, timeFormat());
  }

  // 返回失败
  public static Result failure(String message) {
    Result result = new Result();
    result.setSuccess(false);
    result.setMessage(message);
    result.setCode(400);
    result.setTimestamp(timeFormat());
    return result;
  }

  // 返回失败
  public static Result failure(ResultEnum resultEnum) {
    Result result = new Result();
    result.setSuccess(false);
    result.setResultEnum(resultEnum);
    result.setTimestamp(timeFormat());
    return result;
  }

  // 返回失败
  public static Result failure(int code, String message) {
    Result result = new Result();
    result.setSuccess(false);
    result.setCode(code);
    result.setMessage(message);
    result.setTimestamp(timeFormat());
    return result;
  }

  // 返回失败
  public static Result failure(Object data) {
    Result result = new Result();
    result.setSuccess(false);
    result.setCode(500);
    result.setMessage("系统请求异常！");
    result.setData(data);
    result.setTimestamp(timeFormat());
    return result;
  }

  public void setResultEnum(ResultEnum resultEnum) {
    this.setCode(resultEnum.getCode());
    this.setMessage(resultEnum.getMessage());
  }

  // 时间格式化
  public static String timeFormat() {
    Date date = new Date(System.currentTimeMillis());
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return sdf.format(date);
  }

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}
