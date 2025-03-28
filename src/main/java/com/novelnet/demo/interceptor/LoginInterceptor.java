package com.novelnet.demo.interceptor;

import com.novelnet.demo.util.TokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (request.getMethod().equalsIgnoreCase("OPTIONS")){
            return true;
        }
        if (token == null || token.isEmpty()) {
            response.sendError(401, "请先登录");
            return false;
        }
        Claims claims = TokenUtil.parseToken(token);
        LinkedHashMap<String, Object> user = (LinkedHashMap<String, Object>)claims.get("user");
        request.setAttribute("userId", user.get("uid"));
        return true;
    }
}
