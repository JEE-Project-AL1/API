package com.esgi.jee.apijee.item.exposition.payload;
import com.esgi.jee.apijee.category.exposition.payload.response.CategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private String image;
    private CategoryResponse category;
    private DownloadItemFileDto itemFile;
}
