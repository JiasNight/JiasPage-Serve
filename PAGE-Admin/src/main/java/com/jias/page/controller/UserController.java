package com.jias.page.controller;


import com.jias.page.domain.User;
import com.jias.page.domain.vo.UserPageVo;
import com.jias.page.service.ISysUserService;
import com.jias.page.service.IUserService;
import com.jias.page.utils.resultUtil.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/list")
    public Result getUserList(@RequestBody UserPageVo userPageVo) {
        try {
//            List<MenuVo> menuList = userService.getUserList(userPageVo);
            return Result.success();
        } catch (Exception e) {
            return Result.failure("获取用户列表失败！");
        }
    }

    @Operation(summary  = "用户注册")
    @PostMapping("/signUp")
    public Result newAddUser(@RequestBody User user) {
        return userService.addUserInfo(user);
    }
}
