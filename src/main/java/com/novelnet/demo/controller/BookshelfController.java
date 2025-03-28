package com.novelnet.demo.controller;

import com.novelnet.demo.pojo.Bookshelf;
import com.novelnet.demo.pojo.Result;
import com.novelnet.demo.service.IBookService;
import com.novelnet.demo.service.IBookshelfService;
import com.novelnet.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookshelf")
@CrossOrigin
public class BookshelfController {
    @Autowired
    private IBookshelfService iBookshelfService;

    @Autowired
    private IBookService iBookService;

    @Autowired
    private IUserService iUserService;

    /**
     * 收藏图书的方法
     * 需要参数：bid-图书id
     * 返回值：201-添加成功、400-添加失败、403-重复收藏
     */
    @PostMapping("/token/addBook")
    public Result addBook(@RequestParam("uid") int uid, @RequestParam("bid") int bid){
        if(iBookshelfService.isHaveBookshelf(uid, bid)){
            return new Result(403, null, "addBook ERROR: 重复收藏");
        }
        if(iBookshelfService.addBook(uid, bid) > 0){
            iBookService.addRecommendNum(bid, 10);
            iBookService.addCollectNum(bid, 1);
            return new Result(201, null, "addBook OK!!!");
        }else {
            return new Result(400, null, "addBook ERROR: 收藏失败");
        }
    }

    /**
     * 移除收藏图书的方法
     * 需要参数：bid-图书id（params传参）
     * 返回值：200-移除成功、400-移除失败（重复添加）
     */
    @DeleteMapping("/token/delBook")
    public Result delBook(@RequestParam("uid") int uid, @RequestParam("bid") int bid){
        iBookService.addRecommendNum(bid, -5);
        iBookService.addCollectNum(bid, -1);
        return iBookshelfService.delBook(uid, bid) > 0 ?
                new Result(200, null, "delBook OK!!!") :
                new Result(400, null, "delBook ERROR: 删除失败");
    }

    /**
     * 获取书籍详情
     * @param uid
     * @return
     */
    @GetMapping("/token/getBookshelfByUid")
    public Result getBookshelfByUid(int uid){
        List<Bookshelf> bookshelfList = iBookshelfService.getBookshelfByUid(uid);
        return bookshelfList == null ?
                new Result(400, null, "getBookshelfByUid ERROR") :
                new Result(200, bookshelfList, "getBookshelfByUid OK!!!");
    }

    @GetMapping("/token/getBookshelf")
    public Result getBookshelf(int uid, int bid){
        Bookshelf bookshelf = iBookshelfService.getBookshelf(uid, bid);
        return bookshelf != null ?
                new Result(200, bookshelf, "OK") :
                new Result(400, null, "ERROR");
    }
}
