package com.jias.page.utils.resultUtil;

public enum ResultEnum {

  /**
   * 1XX:信息性状态码，表示服务器已接收到请求，并且正在进一步处理。 2XX:成功状态码，表示服务器成功地接收、理解并处理了请求。
   * 3XX:重定向状态码，表示客户瑞需要进一步采取动作才能完成请求。 4XX:客户端错误状态码，表示客户端发送的请求有误或无法完成。 5XX:服务器错误状态码，表示服务器在处理请求时发生了错误。
   */

  // 默认成功
  SUCCESS(200, "成功"),
  // 默认失败
  REDIRECT(300, "失败"),
  // 默认失败
  FAILURE(400, "失败"),
  // 服务器操作失败
  ERROR(500, "服务器内部错误"),

  /* 业务错误 */
  NO_PERMISSION(401, "没有权限"),
  NO_SERVER(403, "服务器拒绝请求"),
  NO_FIND(404, "服务器找不到请求的网页"),
  NO_FUN(405, "禁用请求中指定的方法"),
  // 下载失败
  DOWNLOAD_ERROR(600, "下载失败"),

  // 查询异常
  SELECT_EXCEPTION(700, "查询异常"),

  /* 参数错误：1000～1999 */
  PARAM_NOT_VALID(1001, "参数无效"),
  PARAM_IS_BLANK(1002, "参数为空"),
  PARAM_TYPE_ERROR(1003, "参数类型错误"),
  PARAM_NOT_COMPLETE(1004, "参数缺失"),

  /* 用户错误 */
  USER_NOT_LOGIN(2001, "用户未登录"),
  USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
  USER_CREDENTIALS_ERROR(2003, "密码错误"),
  USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
  USER_ACCOUNT_DISABLE(2005, "账号不可用"),
  USER_ACCOUNT_LOCKED(2006, "账号被锁定"),
  USER_ACCOUNT_NOT_EXIST(2007, "账号不存在"),
  USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在"),
  USER_ACCOUNT_USE_BY_OTHERS(2009, "账号下线");

  private Integer code;

  private String message;

  ResultEnum(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
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
}
