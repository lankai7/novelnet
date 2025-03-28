package com.novelnet.demo.service.impl;

import com.novelnet.demo.mapper.BookMapper;
import com.novelnet.demo.pojo.Book;
import com.novelnet.demo.pojo.Result;
import com.novelnet.demo.service.IBookService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements IBookService {
    @Resource
    private BookMapper bookMapper;

    @Override
    public List<String> getType() {
        List<Book> bookList = bookMapper.getType();
        return bookList.stream().map(Book::getType).collect(Collectors.toList());
    }

    @Override
    public List<Book> getBooks(String type, String name, int num) {
        List<Book> bookList = bookMapper.getBooks(type, name);
        if (num < 0 || bookList.size() <= num) {
            return bookList;
        }
        return bookList.stream().limit(num).collect(Collectors.toList());
    }

    @Override
    public Book getBook(int bid) {
        return bookMapper.getBook(bid);
    }

    @Override
    public int addRecommendNum(int bid, int num) {
        return bookMapper.addRecommendNum(bid, num);
    }

    @Override
    public int addLoveNum(int bid,int num) {
        return bookMapper.addLoveNum(bid, num);
    }

    @Override
    public int addCollectNum(int bid, int num) {
        return bookMapper.addCollectNum(bid, num);
    }
    @Override
    public int addCommentNum(int bid, int num) {
        return bookMapper.addCommentNum(bid, num);
    }
}