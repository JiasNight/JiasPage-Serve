package com.jias.page.controller;


import com.jias.page.service.ILoginService;
import com.jias.page.utils.resultUtil.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    ILoginService loginService;

    @PostMapping("/login")
    public Result singIn(@RequestParam("username") String userName, @RequestParam("password") String password) {
        System.out.println("username="+userName+";password="+password);
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

    @PostMapping("/")
    public String index() {
        return "index";
    }

}
