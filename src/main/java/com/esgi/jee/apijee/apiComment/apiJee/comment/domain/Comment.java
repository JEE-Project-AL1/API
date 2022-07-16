package com.esgi.jee.apijee.apiComment.apiJee.comment.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "itemId", nullable = false)
    private Long itemId;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "note", nullable = false)
    private int note;

}
