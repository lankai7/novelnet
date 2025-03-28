package com.novelnet.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Chapter {
    private int cid;
    private int chapterNum;
    private String title;
    private String content;
    private String collectTime;
    private Book book;
}
