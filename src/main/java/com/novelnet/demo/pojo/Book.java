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
public class Book {
    private int bid;
    private String name;
    private String author;
    private String updateTime;
    private String introduction;
    private String state;
    private String type;
    private String recommendNum;
    private int collectNum;
    private String issuedTime;
    private String imgPath;
    private int loveNum;
    private int commentNum;
    private List<Chapter> chapters;
    private String createTime;
}
