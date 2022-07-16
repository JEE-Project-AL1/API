package com.esgi.jee.apijee.comment.service;


import com.esgi.jee.apijee.comment.domain.Comment;
import com.esgi.jee.apijee.comment.dto.CommentDto;
import com.esgi.jee.apijee.comment.repository.CommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultCommentService implements CommentService{

    private CommentRepository commentRepository;
    private ModelMapper modelMapper;

    public DefaultCommentService(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;

    }



    @Override
    public CommentDto createComment(CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        Comment newComment = this.commentRepository.save(comment);
        return mapToDto(newComment);
    }

    @Override
    public CommentDto getCommentById(long id) {
        Comment comment = this.commentRepository.getById(id);
        return mapToDto(comment);
    }

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> comments = this.commentRepository.findAll();
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, long id) {
        Comment comment = this.commentRepository.getById(id);

        comment.setUsername(commentDto.getUsername());
        comment.setBody(commentDto.getBody());
        comment.setNote(commentDto.getNote());

        Comment updatedComment = this.commentRepository.save(comment);
        return mapToDto(updatedComment);
    }

    @Override
    public void deleteCommentById(long id) {
        Comment comment = this.commentRepository.findById(id).orElseThrow();
        this.commentRepository.delete(comment);
    }

    @Override
    public List<CommentDto> getCommentsByItemId(long itemId) {
        List<Comment> comments = this.commentRepository.getCommentsByItemId(itemId);
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getCommentsByUserId(long userId) {
        List<Comment> comments = this.commentRepository.getCommentsByUserId(userId);
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());    }

    private CommentDto mapToDto(Comment comment){
        return modelMapper.map(comment, CommentDto.class);
    }

    private Comment mapToEntity(CommentDto commentDto){
        return modelMapper.map(commentDto,Comment.class);
    }
}
