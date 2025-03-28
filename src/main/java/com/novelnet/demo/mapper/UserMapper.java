package com.novelnet.demo.mapper;

import com.novelnet.demo.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User getUserById(@Param("uid") int uid);
    //登录
    User login(@Param("account") String account, @Param("password") String password);
    //验证账号存在
    User isHaveAccount(@Param("account") String account);
    User isHaveEmail(String email);
    //注册
    int enroll(@Param("user") User user);
    //找回密码
    User getUserByAccountAndEmail(@Param("account") String account, @Param("email") String email);
    //修改密码
    int updatePassword(@Param("uid") int uid, @Param("newPassword") String newPassword);
    //获取用户信息（除密码
    User getUserNoPassword(@Param("uid") int uid);
    //修改用户信息
    int updateUser(@Param("user") User user);
    //增加用户积分
    int addUserIntegral(@Param("user") User user, @Param("num") int num);
    //获取全部用户
    List<User> getUserAll();
    //vip状态
    int updateVIPStatus(@Param("userId") Integer userId, @Param("isVip") Integer isVip);

}
