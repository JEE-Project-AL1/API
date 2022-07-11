package com.esgi.jee.apijee.apiComment.apiJee.comment.dto;

import lombok.Data;


@Data
public class CommentDto {

    private Long id;

    private Long itemId;

    private Long userId;

    private String username;

    private String body;

    private int note;
}
