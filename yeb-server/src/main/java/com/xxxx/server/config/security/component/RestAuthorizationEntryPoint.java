package com.xxxx.server.config.security.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxxx.server.pojo.RespBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.System.out;

/**
 * @Author ZedQ
 * @Date 2022/5/10 15:38
 * @ClassName: RestAuthorizationEntryPoint
 * @Description: TODO
 */

@Component
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        httpServletResponse.setCharacterEncoding("UTF-8");
        //设置数据格式为json格式
        httpServletResponse.setContentType("application/json");
        //拿到输出流
        PrintWriter out = httpServletResponse.getWriter();
        //未登录或失效
        RespBean bean = RespBean.error("未登录或用户信息过期，请重新登录！");
        bean.setCode(401);
        out.write(new ObjectMapper().writeValueAsString(bean));
        out.flush();
        out.close();


    }
}
