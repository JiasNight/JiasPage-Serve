package com.jias.page.controller;

import com.jias.page.utils.cryptionUtil.RSAUtil;
import com.jias.page.utils.resultUtil.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@Api(tags = "密钥")
@RequestMapping("/security")
public class SecurityController {

    @ApiOperation(value = "获取公钥", notes = "获取公钥")
    @GetMapping("/pKey")
    public Result getPublicKey() throws Exception {
        Map<String, String> RSAMap = RSAUtil.genKeyPair();
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("pKey", RSAMap.get("publicKey"));
        return Result.success(resultMap);
    }
}
