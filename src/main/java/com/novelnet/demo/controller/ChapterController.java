package com.novelnet.demo.controller;

import com.novelnet.demo.pojo.Chapter;
import com.novelnet.demo.pojo.Result;
import com.novelnet.demo.service.IBookshelfService;
import com.novelnet.demo.service.IChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chapter")
@CrossOrigin
public class ChapterController {
    @Autowired
    private IChapterService iChapterService;

    @Autowired
    private IBookshelfService iBookshelfService;

    @GetMapping("/getChapter")
    public Result getChapter(Integer uid, int bid, Integer chapterNum, Integer num){
        if (uid != null && chapterNum <= 0){
            if(iBookshelfService.isHaveBookshelf(uid, bid)){
                chapterNum = iBookshelfService.getBookshelf(uid, bid).getLastChapter();
            }
        }
        if (chapterNum <= 0){
            chapterNum = 1;
        }
        if(num != null){
            if (num < 0 && chapterNum <= -num){
                return new Result(401, null, "ERROR: 无法更前了");
            }else if (num > 0 && (iChapterService.getChapter(bid, chapterNum + num) == null)){
                return new Result(402, null, "ERROR: 后面无此章节");
            }else {
                chapterNum += num;
            }
        }
        Chapter chapter = iChapterService.getChapter(bid, chapterNum);
        if (chapter != null && uid != null){
            iBookshelfService.updateLastChapter(uid, bid, chapterNum);
        }
        return chapter != null ?
                new Result(200, chapter, "OK") :
                new Result(400, null, "ERROR");
    }
}
