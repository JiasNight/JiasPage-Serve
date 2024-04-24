package com.jias.page.controller;


import com.jias.page.domain.vo.MenuVo;
import com.jias.page.domain.vo.UserPageVo;
import com.jias.page.service.IUserService;
import com.jias.page.utils.resultUtil.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
