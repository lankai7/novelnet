package com.novelnet.demo.service;

import com.novelnet.demo.pojo.User;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface IUserService {
    //登录
    User login(String account, String password);
    //注册
    boolean isHaveEmail(String email);
    int enroll(User user);
    //找回密码
    String getPassword(String account, String email, String newPassword);
    //修改密码
    int updatePassword(int uid, String password, String newPassword);
    //获取登录用户的个人信息
    User getUserNoPassword(int uid);
    //修改用户信息
    int updateUser(User user);
    //获取用户信息
    User getUserById(int uid);
    int addUserIntegral(User user, int num);
    //获取全部用户
    List<User> getUserAll();
    //vip状态
    boolean updateVIPStatus(Integer userId, Integer isVip);
}
