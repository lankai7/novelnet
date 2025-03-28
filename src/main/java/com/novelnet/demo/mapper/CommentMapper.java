package com.novelnet.demo.mapper;
import com.novelnet.demo.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    // 获取指定书籍的评论列表
    List<Comment> getCommentsByBookId(@Param("bookId") int bookId);

    // 添加评论
    int addComment(@Param("comment") Comment comment);

    // 删除评论（修改状态为 'deleted'）
    int deleteComment(@Param("commentId") int commentId);

    // 修改评论内容
    int updateComment(@Param("comment") Comment comment);

    // 获取特定用户的评论（用于用户管理）
    List<Comment> getCommentsByUserId(@Param("userId") int userId);

    List<Comment> getComments();
}
