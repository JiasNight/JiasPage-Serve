package com.jias.page.controller;


import com.jias.page.service.ILoginService;
import com.jias.page.utils.resultUtil.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "登陆相关")
@RequestMapping("/login")
public class LoginController {

    @Autowired
    ILoginService loginService;

    @ApiOperation(value = "用户登陆", notes = "用户登陆")
    @PostMapping("/into")
    public Result singIn(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        try {
            boolean isSigIn = loginService.userIsSigIn(userName, password);
            if (isSigIn) {
                return Result.success();
            } else {
                return Result.failure("登陆失败");
            }
        } catch (Exception e) {
            return Result.failure(e.toString());
        }
    }

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @PostMapping("/add")
    public Result userRegister(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        try {
            boolean isAdd = loginService.userAdd(userName, password);
            if (isAdd) {
                return Result.success();
            } else {
                return Result.failure("注册失败");
            }
        } catch (Exception e) {
            return Result.failure(e.toString());
        }
    }

}
