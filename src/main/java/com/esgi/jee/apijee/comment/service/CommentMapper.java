package com.esgi.jee.apijee.comment.service;

import com.esgi.jee.apijee.comment.domain.Comment;
import com.esgi.jee.apijee.comment.dto.CommentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    private final ModelMapper modelMapper;
    @Autowired
    private CommentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static CommentDto mapToCommentDto(Comment comment){
        CommentDto commentDto = null;
        commentDto.setId(comment.getId());
        commentDto.setItemId(comment.getItemId());
        commentDto.setUserId(comment.getUserId());
        commentDto.setUsername(comment.getUsername());
        commentDto.setBody(comment.getBody());
        commentDto.setNote(comment.getNote());
        return commentDto;
    }
    public CommentDto mapCommentToCommentDto(Comment comment){
        return modelMapper.map(comment, CommentDto.class);
    }
    public Comment mapCommentDtoToComment(CommentDto commentDto){
        return modelMapper.map(commentDto, Comment.class);
    }





}
