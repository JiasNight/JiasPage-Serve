package com.jias.page.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jias.page.enums.ResultEnum;
import com.jias.page.utils.resultUtil.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author JSON
 * @date 2024/4/25
 * @description
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException exception
    ) throws IOException, ServletException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        CommonResult result = userService.getUserByUserName(
//                new UserVO().setUserName(request.getParameter("username")));
//        Users users = (Users) result.getObjectData();
//
//        if (Objects.equals(result.getCode(), YIXGResultEnum.OPERATE_SUCCESS.getCode())) {
//            UserVO userVO = new UserVO();
//            userVO.setUserName(users.getUserName())
//                    .setUserErrCount(String.valueOf((users.getUserErrCount() + 1)))
//                    .setUserLastErrTime(sdf.format(new Date()));
//            userService.updateUserErrCount(userVO);
//        }
        Result result = new Result();
        result.setCode(ResultEnum.USER_CREDENTIALS_ERROR.getCode());
        result.setMessage(ResultEnum.USER_ACCOUNT_DISABLE.getMessage());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().flush();
        response.getWriter().close();
    }
}
