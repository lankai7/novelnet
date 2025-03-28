package com.novelnet.demo.service.impl;

import com.novelnet.demo.mapper.BookshelfMapper;
import com.novelnet.demo.pojo.Bookshelf;
import com.novelnet.demo.service.IBookshelfService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookshelfServiceImpl implements IBookshelfService {
    @Resource
    private BookshelfMapper bookshelfMapper;

    @Override
    public int addBook(int uid, int bid) {
        if(bookshelfMapper.isHaveBookshelf(uid, bid) != null){
            return -1;
        }
        LocalDateTime time = LocalDateTime.now();
        return bookshelfMapper.addBook(uid, bid, time.toString());
    }

    @Override
    public int delBook(int uid, int bid) {
        if(bookshelfMapper.isHaveBookshelf(uid, bid) == null){
            return -1;
        }
        return bookshelfMapper.deleteBook(uid, bid);
    }

    @Override
    public List<Bookshelf> getBookshelfByUid(int uid) {
        return bookshelfMapper.getBookshelfByUid(uid);
    }

    @Override
    public boolean isHaveBookshelf(int uid, int bid) {
        return bookshelfMapper.isHaveBookshelf(uid, bid) != null;
    }

    @Override
    public Bookshelf getBookshelf(int uid, int bid) {
        return bookshelfMapper.getBookshelf(uid, bid);
    }

    @Override
    public int updateLastChapter(int uid, int bid, int chapterNum) {
        return bookshelfMapper.updateLastChapter(uid, bid, chapterNum);
    }
}
