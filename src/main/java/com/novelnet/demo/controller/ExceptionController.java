package com.novelnet.demo.controller;

import com.novelnet.demo.pojo.Result;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler({ExpiredJwtException.class})
    public Result handelException1(Exception e) {
        return new Result(401, null, "USER ERROR: 用户失效");
    }

//    @ExceptionHandler({DuplicateKeyException.class})
//    public Result handelException2(Exception e) {
//        return new Result(504, null, "该账号已存在");
//    }
}
