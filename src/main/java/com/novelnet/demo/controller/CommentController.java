package com.novelnet.demo.controller;

import com.novelnet.demo.pojo.Comment;
import com.novelnet.demo.service.ICommentService;
import com.novelnet.demo.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.novelnet.demo.service.IBookService;
import com.novelnet.demo.pojo.Bookshelf;
import com.novelnet.demo.pojo.Result;
import com.novelnet.demo.service.IBookService;
import com.novelnet.demo.service.IBookshelfService;
import com.novelnet.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IBookshelfService iBookshelfService;

    @Autowired
    private IBookService iBookService;

    @Autowired
    private IUserService iUserService;

    // 获取指定书籍的评论列表
    @GetMapping("/{bookId}")
    public Result getCommentsByBookId(@PathVariable int bookId) {
        if(bookId == 0){
            List<Comment> comments = commentService.getComments();
            if (comments != null && !comments.isEmpty()) {
                return new Result(200, comments, "评论获取成功");
            }
            return new Result(404, null, "没有找到相关评论");
        }
        List<Comment> comments = commentService.getCommentsByBookId(bookId);
        if (comments != null && !comments.isEmpty()) {
            return new Result(200, comments, "评论获取成功");
        }
        return new Result(404, null, "没有找到相关评论");
    }

    // 添加评论
    @PostMapping("/add")
    public Result addComment(@RequestBody Comment comment) {
        boolean success = commentService.addComment(comment);
        if (success) {
            iBookService.addRecommendNum(comment.getBookId(), 15);
            iBookService.addCommentNum(comment.getBookId(), 1);
            return new Result(200, null, "评论添加成功");
        }
        return new Result(400, null, "评论添加失败");
    }

    // 删除评论
    @DeleteMapping("/delete/{commentId}")
    public Result deleteComment(@PathVariable int commentId) {
        boolean success = commentService.deleteComment(commentId);
        if (success) {
            return new Result(200, null, "评论删除成功");
        }
        return new Result(400, null, "评论删除失败");
    }

    // 修改评论
    @PutMapping("/update")
    public Result updateComment(@RequestBody Comment comment) {
        boolean success = commentService.updateComment(comment);
        if (success) {
            return new Result(200, null, "评论更新成功");
        }
        return new Result(400, null, "评论更新失败");
    }

    // 获取指定用户的评论
    @GetMapping("/user/{userId}")
    public Result getCommentsByUserId(@PathVariable int userId) {
        List<Comment> comments = commentService.getCommentsByUserId(userId);
        if (comments != null && !comments.isEmpty()) {
            return new Result(200, comments, "评论获取成功");
        }
        return new Result(404, null, "没有找到该用户的评论");
    }
}
