package com.novelnet.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private int uid;
    private String account;
    private String username;
    private String password;
    private int grade;
    private String email;
    private String sex;
    private String qq;
    private String microblog;
    private String enrollTime;
    private int userIntegral;
    private int messageNum;
    private int bookMaxNum;
    private int collectTicketNum;
    private String personalWeb;
    private List<Book> books;
    private int vipStatus;
    private Integer isAdmin; // 0: 普通用户, 1: 管理员

}
