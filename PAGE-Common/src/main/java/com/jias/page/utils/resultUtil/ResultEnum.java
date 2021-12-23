package com.jias.page.utils.resultUtil;

public enum ResultEnum {

    // 成功
    SUCCESS(200, "成功"),
    // 失败
    FAILURE(222, "失败"),
    // 操作失败
    ERROR(500, "操作失败"),
    // 查询异常
    SELECT_EXCEPTION(700, "查询异常"),
    // 下载失败
    DOWNLOAD_ERROR(600, "下载失败");

    private int code;

    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
