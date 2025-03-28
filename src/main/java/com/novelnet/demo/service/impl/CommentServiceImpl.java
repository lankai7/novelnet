package com.novelnet.demo.service.impl;

import com.novelnet.demo.mapper.CommentMapper;
import com.novelnet.demo.pojo.Comment;
import com.novelnet.demo.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> getCommentsByBookId(int bookId) {
        return commentMapper.getCommentsByBookId(bookId);
    }

    public List<Comment> getComments() {
        return commentMapper.getComments();
    }
    @Override
    public boolean addComment(Comment comment) {
        return commentMapper.addComment(comment) > 0;
    }

    @Override
    public boolean deleteComment(int commentId) {
        return commentMapper.deleteComment(commentId) > 0;
    }

    @Override
    public boolean updateComment(Comment comment) {
        return commentMapper.updateComment(comment) > 0;
    }

    @Override
    public List<Comment> getCommentsByUserId(int userId) {
        return commentMapper.getCommentsByUserId(userId);
    }
}
