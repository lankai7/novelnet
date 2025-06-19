package com.novelnet.demo.interceptor;

import com.novelnet.demo.util.TokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;

public class LoginInterceptor implements HandlerInterceptor {

    private static final Integer ADMIN_ROLE = 1; // 定义管理员常量，代替硬编码

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");

        // 处理OPTIONS请求，避免跨域问题
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            return true;
        }

        // 如果没有 Token，返回401，提示请先登录
        if (token == null || token.isEmpty()) {
            response.sendError(401, "请先登录");
            return false;
        }

        Claims claims = null;
        try {
            // 尝试解析 Token
            claims = TokenUtil.parseToken(token);
        } catch (Exception e) {
            response.sendError(401, "Token 无效或已过期");
            return false;
        }

        // 获取用户信息
        LinkedHashMap<String, Object> user = (LinkedHashMap<String, Object>) claims.get("user");
        if (user == null || user.get("uid") == null) {
            response.sendError(401, "用户信息无效");
            return false;
        }

        // 将 userId 绑定到请求属性，后续可以直接使用
        request.setAttribute("userId", user.get("uid"));

        // 获取 isAdmin 字段
        Integer isAdmin = (Integer) user.get("isAdmin");
        System.out.println("用户ID: " + user.get("uid") + ", isAdmin: " + isAdmin);

        // 获取当前请求路径
        String requestURI = request.getRequestURI();

        // 如果请求路径是管理员相关接口，且用户不是管理员，返回 403 权限不足
        if (requestURI.startsWith("/admin")) {
            if (isAdmin == null || !isAdmin.equals(ADMIN_ROLE)) {
                response.sendError(403, "权限不足");
                return false;
            }
        }

        return true;
    }
}
