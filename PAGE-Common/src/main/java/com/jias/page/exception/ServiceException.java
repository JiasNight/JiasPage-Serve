package com.jias.page.exception;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException {

    private int code;

    private String message;

    private ServiceException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static void throwBiz(int code, String message) {
        throw new ServiceException(code, message);
    }
}
