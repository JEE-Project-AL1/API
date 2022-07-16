package com.esgi.jee.apijee.apiComment.apiJee.Item.controller;


import com.esgi.jee.apijee.apiComment.apiJee.Item.dto.ItemDto;
import com.esgi.jee.apijee.apiComment.apiJee.Item.dto.SearchDto;
import com.esgi.jee.apijee.apiComment.apiJee.Item.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    private ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }
    @PostMapping
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemDto itemDto) {
        return new ResponseEntity<>(this.itemService.createItem(itemDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getUserById(@PathVariable(name = "id") long id){
        return new ResponseEntity<>(this.itemService.getItemById(id),HttpStatus.OK);}

    @GetMapping
    public List<ItemDto> getAllItems(){
        return this.itemService.getAllItems();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDto> updateItem(@PathVariable(name = "id") long id,@RequestBody ItemDto itemDto){
        return new ResponseEntity<>(this.itemService.updateItem(itemDto, id), HttpStatus.OK);
    }
    @GetMapping("/search/{name}")
    public List<SearchDto> getItemByName(@PathVariable(name="name") String name){
        List<SearchDto> search = this.itemService.getItemByName(name);
        return search;
    }

    @DeleteMapping("/{id}")
    public void deleteItemById(@PathVariable(name = "id") long id){
        this.itemService.deleteItemById(id);
    }
}
