package com.esgi.jee.apijee.apiComment.apiJee.Item.service;


import com.esgi.jee.apijee.apiComment.apiJee.Item.dto.ItemDto;
import com.esgi.jee.apijee.apiComment.apiJee.Item.dto.SearchDto;

import java.util.List;

public interface ItemService {
    ItemDto createItem(ItemDto itemDto);
    ItemDto getItemById(long id);
    List<ItemDto> getAllItems();
    ItemDto updateItem(ItemDto itemDto, long id);
    void deleteItemById(long id);
    List<SearchDto> getItemByName(String name);

}
