package com.page.auth.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.page.common.enums.ResultEnum;
import com.page.common.utils.resultUtil.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import java.io.IOException;

/**
 * @author JSON
 * @date 2024/5/31
 * @description
 */
public class MySessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        Result result = Result.success(ResultEnum.SIGN_OUT_SUCCESS);
        HttpServletResponse response = event.getResponse();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
