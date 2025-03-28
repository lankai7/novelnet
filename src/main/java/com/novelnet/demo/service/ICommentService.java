package com.novelnet.demo.service;


import com.novelnet.demo.pojo.Comment;

import java.util.List;
public interface ICommentService {

    // 获取指定书籍的评论
    List<Comment> getCommentsByBookId(int bookId);

    // 添加评论
    boolean addComment(Comment comment);

    // 删除评论
    boolean deleteComment(int commentId);

    // 修改评论
    boolean updateComment(Comment comment);

    // 获取特定用户的评论
    List<Comment> getCommentsByUserId(int userId);

    List<Comment> getComments();
}
