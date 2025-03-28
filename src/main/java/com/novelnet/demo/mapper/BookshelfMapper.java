package com.novelnet.demo.mapper;

import com.novelnet.demo.pojo.Bookshelf;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookshelfMapper {
    //收藏图书
    int addBook(@Param("uid") int uid, @Param("bid") int bid, @Param("collectTime") String collectTime);
    //判断图书是否存在
    Bookshelf isHaveBookshelf(@Param("uid") int uid, @Param("bid") int bid);
    //删除收藏图书
    int deleteBook(@Param("uid") int uid, @Param("bid") int bid);
    //查看收藏的图书
    List<Bookshelf> getBookshelfByUid(@Param("uid") int uid);
    Bookshelf getBookshelf(@Param("uid") int uid, @Param("bid") int bid);
    //更新观看的书签页
    int updateLastChapter(@Param("uid") int uid, @Param("bid") int bid, @Param("chapterNum") int chapterNum);
}
