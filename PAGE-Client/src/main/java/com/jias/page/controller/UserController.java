package com.jias.page.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jias.page.service.IUserService;
import com.jias.page.utils.resultUtil.Result;
import com.jias.page.utils.resultUtil.ResultEnum;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/download")
    public void downloadFile(HttpServletResponse response){
        try {
            File file = new File("/home/jias/Desktop/地区坐标.xlsx");
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.addHeader("Content-Disposition", "attachment;fileName=" + file.getName());
            OutputStream os = response.getOutputStream();
            FileCopyUtils.copy(fis,os);
            bis.close();
            fis.close();
            os.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @GetMapping("/upload")
    public Result uploadFile(@RequestParam("file") MultipartFile file) {
        // 获取文件名
        String fileOrigName = file.getOriginalFilename();
        // 如果同一张图片怕覆盖，也可以加个时间戳
        //String fileOrigName = System.currentTimeMillis() + file.getOriginalFilename();
        String filePath = "upload/" + fileOrigName;
        try {
            File saveFile = new File(filePath);
            // 如果目录不存在，创建目录
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
//            file.transferTo(saveFile);
            FileOutputStream fos = new FileOutputStream(saveFile);
            byte[] bytes = file.getBytes();
            fos.write(bytes);
            fos.close();
        } catch (Exception e) {
            return Result.failure(e.toString());
        }
        return Result.success(filePath);
    }

    /**
     * 获取用户信息列表
     * @return
     */
    @GetMapping("/userList")
    public Result getUserList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        try {
            Page pageInfo = new Page(pageNum, pageSize);
            IPage userList = userService.getUserList(pageInfo);
            return Result.success(userList);
        } catch (Exception e) {
            return Result.failure(ResultEnum.SELECT_EXCEPTION);
        }
    }

    /**
     * 添加用户信息列表
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    public Result addUserInfo(@RequestBody User user) {
        try {
            boolean bool = userService.addUserInfo(user);
            if (bool) {
                return Result.success();
            } else {
                return Result.failure();
            }
        } catch (Exception e) {
            return Result.failure();
        }
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @GetMapping("/delUser")
    public Result delUserInfo(@RequestParam(value = "userId", required = true) String userId) {
        try {
            boolean bool = userService.delUserInfo(userId);
            if (bool) {
                return Result.success();
            } else {
                return Result.failure();
            }
        }catch (Exception e) {
            return Result.failure();
        }
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @PostMapping("/updateUser")
    public Result updateUserInfo(@RequestBody User user) {
        try {
            boolean b = userService.updateUserInfo(user);
            if (b) {
                return Result.success();
            } else {
                return Result.failure();
            }
        }catch (Exception e) {
            return Result.failure();
        }
    }

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    @GetMapping("/getUserInfo")
    public Result selectUserInfoById(@RequestParam("userId") String userId) {
        try {
            Map userInfo = userService.selectUserInfoById(userId);
            return Result.success(userInfo);
        } catch (Exception e) {
            return Result.failure(ResultEnum.SELECT_EXCEPTION);
        }
    }
}
