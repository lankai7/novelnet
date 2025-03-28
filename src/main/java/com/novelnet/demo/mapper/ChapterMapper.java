package com.novelnet.demo.mapper;

import com.novelnet.demo.pojo.Chapter;
import org.apache.ibatis.annotations.Param;

public interface ChapterMapper {
    Chapter getChapter(@Param("bid") int bid, @Param("chapterNum") int chapterNum);
}
