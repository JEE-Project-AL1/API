package com.esgi.jee.apijee.item.exposition.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DownloadItemFileDto {
    private byte[] file;
    private String title;
    private String description;
    private String details;


}
