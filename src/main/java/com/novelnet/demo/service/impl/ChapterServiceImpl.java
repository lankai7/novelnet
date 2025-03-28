package com.novelnet.demo.service.impl;

import com.novelnet.demo.mapper.ChapterMapper;
import com.novelnet.demo.pojo.Chapter;
import com.novelnet.demo.service.IChapterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ChapterServiceImpl implements IChapterService {
    @Resource
    private ChapterMapper chapterMapper;

    @Override
    public Chapter getChapter(int bid, int chapterNum) {
        return chapterMapper.getChapter(bid, chapterNum);
    }
}
