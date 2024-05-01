package com.jias.page.annotation;

import java.lang.annotation.*;

/**
 * @author JSON
 * @date 2024/4/25
 * @description JWT验证忽略注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JwtIgnore {
}
