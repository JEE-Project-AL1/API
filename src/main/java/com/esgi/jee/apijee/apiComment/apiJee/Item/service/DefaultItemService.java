package com.esgi.jee.apijee.apiComment.apiJee.Item.service;


import com.esgi.jee.apijee.apiComment.apiJee.Item.domain.Item;
import com.esgi.jee.apijee.apiComment.apiJee.Item.dto.ItemDto;
import com.esgi.jee.apijee.apiComment.apiJee.Item.dto.SearchDto;
import com.esgi.jee.apijee.apiComment.apiJee.Item.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultItemService implements ItemService{

    private ItemRepository itemRepository;
    private ModelMapper modelMapper;

    public DefaultItemService(ItemRepository itemRepository, ModelMapper modelMapper) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;

    }


    @Override
    public ItemDto createItem(ItemDto itemDto) {
        Item item = mapToEntity(itemDto);
        Item newItem = this.itemRepository.save(item);
        return mapToDto(newItem);
    }

    @Override
    public ItemDto getItemById(long id) {
        Item item = this.itemRepository.getById(id);
        return mapToDto(item);
    }

    @Override
    public List<ItemDto> getAllItems() {
        List<Item> items = this.itemRepository.findAll();
        return items.stream().map(item -> mapToDto(item)).collect(Collectors.toList());
    }

    @Override
    public ItemDto updateItem(ItemDto itemDto, long id) {
        Item item = this.itemRepository.getById(id);

        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setPrice(itemDto.getPrice());

        Item updatedItem = this.itemRepository.save(item);
        return mapToDto(updatedItem);    }

    @Override
    public void deleteItemById(long id) {
        Item item = this.itemRepository.findById(id).orElseThrow();
        this.itemRepository.delete(item);
    }

    @Override
    public List<SearchDto> getItemByName(String name) {
        List<Item> items = this.itemRepository.findAll();
        List<SearchDto> result = resultSearchItem(name,items);
        return result;
    }

    public static List<SearchDto> resultSearchItem(String searchForUser, List<Item> items) {
        List<SearchDto> results = new ArrayList<SearchDto>();
        searchForUser = searchForUser.toLowerCase();
        String itemName;
        for (int k = 0; k < items.size(); k++) {
            itemName = items.get(k).getName().toLowerCase();
            int[] costs = new int[itemName.length() + 1];
            for (int j = 0; j < costs.length; j++)
                costs[j] = j;
            for (int i = 1; i <= searchForUser.length(); i++) {
                costs[0] = i;
                int nw = i - 1;
                for (int j = 1; j <= itemName.length(); j++) {
                    int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), searchForUser.charAt(i - 1) == itemName.charAt(j - 1) ? nw : nw + 1);
                    nw = costs[j];
                    costs[j] = cj;
                }
            }
            int compt = 0;
            if (costs[itemName.length()] <= 3) {
                SearchDto item = new SearchDto(items.get(k), costs[itemName.length()]);
                results.add(compt, item);
                compt++;
            }
        }
        return searchOrder(results);
    }


    public static List<SearchDto> searchOrder(List<SearchDto> items) {
        Collections.sort(items, SearchDto.ComparatorDifference);
        return items;
    }

    private ItemDto mapToDto(Item item){
        return modelMapper.map(item, ItemDto.class);
    }

    private Item mapToEntity(ItemDto ItemDto){
        return modelMapper.map(ItemDto,Item.class);
    }
}
