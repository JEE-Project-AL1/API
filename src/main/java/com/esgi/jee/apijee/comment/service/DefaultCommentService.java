package com.esgi.jee.apijee.comment.service;


import com.esgi.jee.apijee.comment.domain.Comment;
import com.esgi.jee.apijee.comment.dto.CommentDto;
import com.esgi.jee.apijee.comment.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultCommentService implements CommentService{

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public DefaultCommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }


    @Override
    public CommentDto createComment(CommentDto commentDto) {
        Comment comment = this.commentMapper.mapCommentDtoToComment(commentDto);
        Comment newComment = this.commentRepository.save(comment);
        return CommentMapper.mapToCommentDto(newComment);
    }

    @Override
    public CommentDto getCommentById(long id) {
        Comment comment = this.commentRepository.getById(id);
        return CommentMapper.mapToCommentDto(comment);
    }

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> comments = this.commentRepository.findAll();
        return comments.stream().map(comment -> CommentMapper.mapToCommentDto(comment)).collect(Collectors.toList());    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, long id) {
        Comment comment = this.commentRepository.getById(id);

        comment.setUsername(commentDto.getUsername());
        comment.setBody(commentDto.getBody());
        comment.setNote(commentDto.getNote());

        Comment updatedComment = this.commentRepository.save(comment);
        return CommentMapper.mapToCommentDto(updatedComment);
    }

    @Override
    public void deleteCommentById(long id) {
        Comment comment = this.commentRepository.findById(id).orElseThrow();
        this.commentRepository.delete(comment);
    }

    @Override
    public List<CommentDto> getCommentsByItemId(long itemId) {
        List<Comment> comments = this.commentRepository.getCommentsByItemId(itemId);
        return comments.stream().map(comment -> CommentMapper.mapToCommentDto(comment)).collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getCommentsByUserId(long userId) {
        List<Comment> comments = this.commentRepository.getCommentsByUserId(userId);
        return comments.stream().map(comment -> CommentMapper.mapToCommentDto(comment)).collect(Collectors.toList());    }

}
