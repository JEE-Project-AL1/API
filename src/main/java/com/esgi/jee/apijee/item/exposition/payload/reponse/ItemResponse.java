package com.esgi.jee.apijee.item.exposition.payload.reponse;
import com.esgi.jee.apijee.category.exposition.payload.response.CategoryResponse;
import com.esgi.jee.apijee.item.exposition.payload.DownloadItemFileDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemResponse {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;
    private CategoryResponse category;
    private DownloadItemFileDto itemFile;
}
