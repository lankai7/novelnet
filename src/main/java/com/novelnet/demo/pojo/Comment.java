package com.novelnet.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {

    private int commentId;
    private int bookId;
    private int userId;
    private String userName; // 新增字段
    private String commentText;
    private String createTime;
    private String status;

    // Getters and Setters
}