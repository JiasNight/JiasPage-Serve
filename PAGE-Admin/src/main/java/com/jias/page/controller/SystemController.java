package com.jias.page.controller;

import com.jias.page.utils.cryptionUtil.AESUtil;
import com.jias.page.utils.redisUtil.RedisUtil;
import com.jias.page.utils.resultUtil.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "系统相关")
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "获取AES加密密钥", notes = "AES加密密钥")
    @GetMapping("/aesKey")
    public Result getCommonAesKey()  {
        try {
            String secretKey = AESUtil.getSecretKey(16);
            System.out.println(secretKey);
            redisUtil.set("aesKey", secretKey);
            return Result.success(secretKey);
        } catch (Exception e) {
            return Result.failure("获取密钥失败！");
        }
    }
}
