package com.esgi.jee.apijee.comment.service;


import com.esgi.jee.apijee.comment.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto);
    CommentDto getCommentById(long id);
    List<CommentDto> getAllComments();
    CommentDto updateComment(CommentDto commentDto, long id);
    void deleteCommentById(long id);
    List<CommentDto> getCommentsByItemId(long itemId);
    List<CommentDto> getCommentsByUserId(long userId);

}
