package com.novelnet.demo.service;

import com.novelnet.demo.pojo.Chapter;

public interface IChapterService {
    Chapter getChapter(int bid, int chapterNum);
}
