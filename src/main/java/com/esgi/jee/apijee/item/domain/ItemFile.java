package com.esgi.jee.apijee.item.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="item_file")
public class ItemFile {
    @Id
    @Column(name = "file_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long fileId;
    private String fileLink;
    private String title;
    private String description;
    private String details;
    public ItemFile(String fileLink, String title, String description, String details) {
        this.fileLink = fileLink;
        this.title = title;
        this.description = description;
        this.details = details;
    }

}
