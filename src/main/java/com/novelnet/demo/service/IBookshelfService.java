package com.novelnet.demo.service;

import com.novelnet.demo.pojo.Bookshelf;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface IBookshelfService {
    int addBook(int uid, int bid);
    int delBook(int uid, int bid);
    List<Bookshelf> getBookshelfByUid(int uid);
    boolean isHaveBookshelf(int uid, int bid);
    Bookshelf getBookshelf(int uid, int bid);
    int updateLastChapter(int uid, int bid, int chapterNum);
}
