package com.esgi.jee.apijee.item.application;
import com.esgi.jee.apijee.category.exposition.payload.response.CategoryResponse;
import com.esgi.jee.apijee.item.domain.Item;
import com.esgi.jee.apijee.item.exposition.payload.DownloadItemFileDto;
import com.esgi.jee.apijee.item.exposition.payload.ItemDto;
import com.esgi.jee.apijee.item.exposition.payload.SearchDto;
import com.esgi.jee.apijee.item.exposition.payload.reponse.ItemResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    private final ModelMapper modelMapper;
    @Autowired
    private ItemMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static ItemResponse mapToItemResponse(Item item, DownloadItemFileDto itemFileDto){
        ItemResponse itemResponse= new ItemResponse();
        itemResponse.setId(item.getId());
        itemResponse.setImage(item.getImage());
        itemResponse.setName(item.getName());
        itemResponse.setPrice(item.getPrice());
        itemResponse.setDescription(item.getDescription());
        itemResponse.setItemFile(itemFileDto);
        itemResponse.setCategory(new CategoryResponse(item.getCategory().getId(),item.getCategory().getName()));
       return itemResponse;
    }
    public ItemDto mapItemResponseToItemDto(ItemResponse item){
        return modelMapper.map(item, ItemDto.class);
    }
    public ItemResponse mapSearchDToItemResponse(SearchDto item){
        return modelMapper.map(item, ItemResponse.class);
    }

}
