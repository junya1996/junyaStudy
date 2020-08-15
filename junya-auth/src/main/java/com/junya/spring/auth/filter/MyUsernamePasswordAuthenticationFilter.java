package com.junya.spring.auth.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Author Junya
 * Date 2020-08-14 16:36
 * Description:
 */
public class MyUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public MyUsernamePasswordAuthenticationFilter() {
        //指定登陆路径
        super(new AntPathRequestMatcher("/v1/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        if (!"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        if (userName == null) {
            userName = "";
        }
        if (password == null) {
            password = "";
        }
        userName = userName.trim();
        //封装到security提供的用户认证接口中
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                userName, password);
        /*将登陆请求提交给认证 AuthenticationManager管理模块 下的authenticate方法 再由authenticate具体的实现类完成认证服务
        使用默认提供的DaoAuthenticationProvider 这个用户信息查询及存储实现类  */
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
