package com.novelnet.demo.controller;

import com.novelnet.demo.pojo.Result;
import com.novelnet.demo.pojo.User;
import com.novelnet.demo.service.IUserService;
import com.novelnet.demo.util.SendMail;
import com.novelnet.demo.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private IUserService iUserService;

    /**
     * 登录方法
     * 需要参数：account-账号、password-密码
     * 状态码：200-成功， 401-用户名或密码错误
     */
    @PostMapping("/login")
    public Result login(String account, String password){
        User user = iUserService.login(account, password);
        if (user != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("user", user);
            String token = TokenUtil.makeToken(map);
            return new Result(200, token, "登陆成功！");
        }
        return new Result(401, null, "LOGIN ERROR: 用户名或密码错误");
    }

    /**
     * 注册方法
     * 需要参数：JSON对象，对应User
     * 状态码：201-注册成功、403-用户已存在、400-注册失败
     */
    @PostMapping("/enroll")
    public Result enroll(@RequestBody User user){
        user.setUsername(user.getAccount());
        int i = iUserService.enroll(user);
        if(i == 1){
            return new Result(201, null, "您已成功注册");
        }else if(i == -1){
            return new Result(403, null, "ENROLL ERROR: 用户名已存在");
        }else {
            return new Result(400, null, "ENROLL ERROR: 注册失败");
        }
    }

    /**
     * 找回密码方法，返回密码字符串
     * 需要参数：account-账号、email-邮箱
     * 200-成功，允许进行修改、404-失败，用户名或邮箱错误
     */
    @PostMapping("/retrievePassword")
    public Result retrievePassword(String account, String email, String newPassword){
        String password = iUserService.getPassword(account, email, newPassword);
        if (password == null){
            return new Result(404, null, "retrievePassword ERROR: 请检查用户名邮箱是否正确");
        }
        return new Result(200, null, "密码已修改成功！");
    }

    /**
     * 修改密码的方法（需要登录以后）
     * 需要参数：uid-用户id、password-原密码、newPassword-新密码
     * 200-修改密码成功、400-修改密码失败，原密码错误
     */
    @PostMapping("/token/updatePassword")
    public Result updatePassword(int uid, String password, String newPassword){
        return iUserService.updatePassword(uid, password, newPassword) > 0 ?
                new Result(200, null, "修改密码成功！") :
                new Result(400, null, "updatePassword ERROR: 原密码错误");
    }

    /**
     * 获取用户详细信息
     * 需要参数：account-账号、password-密码
     * 200-获取成功
     */
    @PostMapping("/token/getUser")
    public Result getUserByAccount(String account, String password){
        User user = iUserService.login(account, password);
        if (user != null) {
            return new Result(200, user, "获取成功！");
        }
        return new Result(401, null, "LOGIN ERROR: 用户名或密码错误");
    }

    /**
     * 修改用户信息
     * 需要修改完成以后的用户作为参数，传参类型为JSON
     * 200-修改成功，400-修改失败
     */
    @PutMapping("/updateUser")
    public Result updateUser(@RequestBody User user){
        user.setUid(user.getUid());
        return iUserService.updateUser(user) > 0 ?
                new Result(200, null, "修改成功！") :
                new Result(400, null, "updateUser ERROR: 修改失败");
    }
    /**
     * 获取用户详细信息
     * 需要参数：uid-用户编号
     * 200-获取成功
     */
    @GetMapping("/token/getUserById/{uid}")
    public Result getUserById(@PathVariable int uid){
        User user = iUserService.getUserById(uid);
        return user != null ?
                new Result(200, user, "updateUser OK!!!") :
                new Result(400, null, "updateUser ERROR!");
    }

    @GetMapping("/mailboxVerification")
    public Result mailboxVerification(@RequestParam("email") String email ,Boolean index){
        String verificationCode = null;
        try {
            if(index){
                if(iUserService.isHaveEmail(email)){
                    return new Result(409,null,"此邮箱已被注册！");
                }
            }
            verificationCode = SendMail.mailboxVerification(email);
        } catch (MessagingException e) {
            e.printStackTrace();
            new Result(400, null, "验证码获取失败!");
        }

        return verificationCode != null ?
                new Result(200, verificationCode, "验证码已发送成功！") :
                new Result(400, null, "验证码获取失败！");
    }
    /**
     * 获取所有用户详细信息
     * 200-获取成功
     */
    @GetMapping("/token/getUserAll")
    public Result getUserAll(){
        List<User> user = iUserService.getUserAll();
        return user != null ?
                new Result(200, user, "updateUser OK!!!") :
                new Result(400, null, "updateUser ERROR!");
    }
    /**
     * 修改用户vip信息
     * 200-修改成功
     */
    @PutMapping("/updateVIP")
    public ResponseEntity<Map<String, Object>> updateVIP(@RequestBody Map<String, Object> payload) {
        Integer userId = (Integer) payload.get("uid");
        Integer isVip = (Integer) payload.get("isVip");

        Map<String, Object> response = new HashMap<>();
        if (userId == null || isVip == null) {
            response.put("code", 400);
            response.put("msg", "参数错误");
            return ResponseEntity.badRequest().body(response);
        }

        boolean success = iUserService.updateVIPStatus(userId, isVip);
        if (success) {
            response.put("code", 200);
            response.put("msg", "VIP 状态更新成功");
        } else {
            response.put("code", 500);
            response.put("msg", "VIP 状态更新失败");
        }
        return ResponseEntity.ok(response);
    }
}
