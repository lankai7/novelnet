package com.novelnet.demo.mapper;

import com.novelnet.demo.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
    //获取图书类型
    List<Book> getType();
    //查所有图书
    List<Book> getBooks(@Param("type") String type, @Param("name") String name, @Param("orderBy")String orderBy);
    //查图书详情
    Book getBook(@Param("bid") int bid);
    //增加推荐数，点赞数,收藏数,评论数
    int addRecommendNum(@Param("bid") int bid, @Param("num") int num);
    int addLoveNum(@Param("bid") int bid, @Param("num") int num);
    int addCollectNum(@Param("bid") int bid, @Param("num") int num);
    int addCommentNum(@Param("bid") int bid, @Param("num") int num);
}
