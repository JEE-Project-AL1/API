package com.esgi.jee.apijee.item.exposition.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemUpdateRequest {
    private Long id;
    private MultipartFile file;
    private String name;
    private String description;
    private Double price;
    private String image;
    private String category;
}
