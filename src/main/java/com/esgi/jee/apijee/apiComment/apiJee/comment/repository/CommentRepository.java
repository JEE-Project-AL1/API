package com.esgi.jee.apijee.apiComment.apiJee.comment.repository;


import com.esgi.jee.apijee.apiComment.apiJee.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> getCommentsByItemId(long itemId);
    List<Comment> getCommentsByUserId(long userId);
}
