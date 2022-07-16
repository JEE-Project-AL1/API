package com.esgi.jee.apijee.apiComment.apiJee.Item.dto;

import lombok.Data;

@Data
public class ItemDto {
    public Long id;
    public String name;
    public String description;
    public int price;
}
