package com.page.common.utils.cryptionUtil;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author sungang
 * @date 2021/10/15 2:54 下午
 * 捕获报错日志处理工具类
 */
public class ErrorUtil {

    /**
     * Exception出错的栈信息转成字符串
     * 用于打印到日志中
     */
    public static String errorInfoToString(Throwable e) {
        //try-with-resource语法糖 处理机制
        try (StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw)) {
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
            return sw.toString();
        } catch (Exception ignored) {
            throw new RuntimeException(ignored.getMessage(), ignored);
        } finally{

        }
    }
}

