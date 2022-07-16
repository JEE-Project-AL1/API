package com.esgi.jee.apijee.comment.controller;


import com.esgi.jee.apijee.comment.dto.CommentDto;
import com.esgi.jee.apijee.comment.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(this.commentService.createComment(commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(name = "id") long id){
        return new ResponseEntity<>(this.commentService.getCommentById(id),HttpStatus.OK);}

    @GetMapping
    public List<CommentDto> getAllComments(){
        return this.commentService.getAllComments();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(name = "id") long id,@RequestBody CommentDto commentDto){
        return new ResponseEntity<>(this.commentService.updateComment(commentDto, id), HttpStatus.OK);
    }

    @GetMapping("/userId/{userId}")
    public List<CommentDto> getCommentsByUserId(@PathVariable(name="userId") long userId){
        return this.commentService.getCommentsByUserId(userId);
    }

    @GetMapping("/itemId/{itemId}")
    public List<CommentDto> getCommentsByItemId(@PathVariable(name="itemId") long itemId){
        return this.commentService.getCommentsByItemId(itemId);
    }

    @DeleteMapping("/{id}")
    public void deleteCommentById(@PathVariable(name = "id") long id){
        this.commentService.deleteCommentById(id);
    }
}
