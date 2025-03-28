package com.novelnet.demo.service;

import com.novelnet.demo.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IBookService {
    //获取图书类型
    List<String> getType();
    //获取图书
    List<Book> getBooks(String type, String name, int num);
    //获取图书详情
    Book getBook(int bid);
    //点赞
    int addRecommendNum(int bid, int num);
    int addLoveNum(int bid, int num);
    int addCollectNum(int bid, int num);
    int addCommentNum(int bid, int num);

}
