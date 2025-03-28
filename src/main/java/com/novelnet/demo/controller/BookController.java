package com.novelnet.demo.controller;

import com.novelnet.demo.pojo.Book;
import com.novelnet.demo.pojo.Result;
import com.novelnet.demo.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@CrossOrigin
public class BookController {
    @Autowired
    private IBookService iBookService;

    /**
     * 获取图书所有类型的方法，返回字符串集合
     * 无需要参数
     * 返回值：200-成功
     */
    @GetMapping("/getType")
    public Result getType(){
        return new Result(200, iBookService.getType(), "getType OK!!!");
    }

    /**
     * 获取图书的方法
     * 需要参数：type-类型（选填）、name-书名（选填）、num-获取书的数量（小于零获取全部）（传递x-www-form-urlencoded类型数据，通过请求头设置）
     * 返回值：200-成功
     */
    @GetMapping("/getBooks")
    public Result getBooks(String type, String name, int num){
        List<Book> books = iBookService.getBooks(type, name, num);
        return new Result(200, books, "getBooks OK!!!");
    }

    /**
     * 获取图书详情的方法（包括图书章节信息）
     * 需要参数：bid-图书id (路径拼参）
     * 返回值：200-成功
     */
    @GetMapping("/getBook/{bid}")
    public Result getBook(@PathVariable("bid") int bid){
        Book book = iBookService.getBook(bid);
        return new Result(200, book, "getBook OK!!!");
    }

    /**
     * 给书籍点赞
     * @param bid
     * @return
     */
    @PutMapping("/token/thumbsUp")
    public Result thumbsUp(@RequestParam("bid") int bid){
        iBookService.addRecommendNum(bid, 5);
        return iBookService.addLoveNum(bid, 1) > 0 ?
                new Result(200, null, "OK") :
                new Result(400, null, "ERROR");
    }
}
