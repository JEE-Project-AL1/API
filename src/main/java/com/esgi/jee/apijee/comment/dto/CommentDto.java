package com.esgi.jee.apijee.comment.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class CommentDto {

    private Long id;

    private Long itemId;

    private Long userId;

    private String username;

    private String body;

    private int note;

    public CommentDto(Long id, Long itemId, Long userId, String username, String body, int note) {
        this.id = id;
        this.itemId = itemId;
        this.userId = userId;
        this.username = username;
        this.body = body;
        this.note = note;
    }
}
