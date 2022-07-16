package com.esgi.jee.apijee.item.application;

import com.esgi.jee.apijee.category.domain.Category;
import com.esgi.jee.apijee.category.infrastructure.CategoryRepository;
import com.esgi.jee.apijee.item.domain.Item;
import com.esgi.jee.apijee.item.domain.ItemFile;
import com.esgi.jee.apijee.item.exposition.payload.DownloadItemFileDto;
import com.esgi.jee.apijee.item.exposition.payload.ItemDto;
import com.esgi.jee.apijee.item.exposition.payload.SearchDto;
import com.esgi.jee.apijee.item.exposition.payload.reponse.ItemResponse;
import com.esgi.jee.apijee.item.exposition.payload.request.ItemRequest;
import com.esgi.jee.apijee.item.exposition.payload.request.ItemUpdateRequest;
import com.esgi.jee.apijee.item.infrastructure.ItemRepository;
import com.esgi.jee.apijee.kernel.utils.FileStore;
import com.esgi.jee.apijee.kernel.utils.bucket.BucketName;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.http.entity.ContentType.*;

@Slf4j
@Service
@AllArgsConstructor
public class ItemService {
    private static final List IMAGES_EXE = Arrays.asList(IMAGE_GIF.getMimeType(),
            IMAGE_PNG.getMimeType(),
            IMAGE_JPEG.getMimeType());
    public FileStore fileStore;

    private ItemRepository itemRepository;
    private CategoryRepository categoryRepository;

    private ItemMapper itemMapper;

    public List<ItemResponse> getItems() {
        log.info("Fetching all items ");
        return itemRepository.findAll().stream().filter(item -> item.getDisabled().equals(Boolean.FALSE)).map(item-> getItemById(item.getId())).collect(Collectors.toList());
    }

    public List<ItemResponse> getItemsByCategory(String category) {
        log.info("Fetching all items by category ");
        Category categorySearched = categoryRepository.findByName(category).orElseThrow(()->new com.esgi.jee.apijee.kernel.exceptions.EntityNotFoundException());
        return itemRepository.findAllByCategory(categorySearched).stream().filter(item -> item.getDisabled().equals(Boolean.FALSE)).map(item-> getItemById(item.getId())).collect(Collectors.toList());
    }


    public Item saveItem(ItemRequest itemRequest) {
        //1. Chek if image is not empty
        if(itemRequest.getFile().isEmpty()){
            throw new IllegalStateException(String.format("Cannot upload empty file [%d] ", itemRequest.getFile().getSize()));
        }
        //2. If file is an image
        if(!IMAGES_EXE.contains(itemRequest.getFile().getContentType())){
            throw new IllegalStateException(String.format("File must be an image"));
        }
        //3. Grap some metadata from file if any
        Map<String,String> metadata= new HashMap<>();
        metadata.put("Content-Type", itemRequest.getFile().getContentType());
        metadata.put("Content-Length",String.valueOf(itemRequest.getFile().getSize()));
        //5. Store the image in s3 and Update BDD with s3 image link
        String path = String.format("%s", BucketName.PROFILE_FILE.getBucketName());
        String fileName = String.format("%s-%s", itemRequest.getFile().getOriginalFilename(),UUID.randomUUID());
        Item item = new Item();
        try{
            fileStore.save(path,fileName,Optional.of(metadata), itemRequest.getFile().getInputStream());
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            ItemFile file = new ItemFile( fileName, itemRequest.getName(), itemRequest.getDescription(), dateFormat.format(Calendar.getInstance().getTime()));
            item.setFile(file);
        }catch (IOException e){
            throw new IllegalStateException(e);
        }
        item.setName(itemRequest.getName());
        item.setPrice(itemRequest.getPrice());
        item.setDescription(itemRequest.getDescription());
        item.setDisabled(Boolean.FALSE);
        Optional<Category> category = categoryRepository.findByName(itemRequest.getCategory());
        if(category.isPresent()) item.setCategory(category.get());
        return itemRepository.save(item);
    }



    public ItemResponse getItemById(Long itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        if(item.isEmpty()){
            throw new IllegalStateException("Item not found !!");
        }
        String path = String.format("%s", BucketName.PROFILE_FILE.getBucketName());
        ItemFile file = item.get().getFile();
        DownloadItemFileDto itemFileDto = new DownloadItemFileDto(fileStore.download(path, file.getFileLink()), file.getTitle(), file.getDescription(), file.getDetails());
        return ItemMapper.mapToItemResponse(item.get(),itemFileDto);
    }

    public Item updateItem(ItemUpdateRequest itemRequest) {
        //1. Chek if image is not empty
        if(itemRequest.getFile().isEmpty()){
            throw new IllegalStateException(String.format("Cannot upload empty file [%d] ", itemRequest.getFile().getSize()));
        }
        //2. If file is an image
        if(!IMAGES_EXE.contains(itemRequest.getFile().getContentType())){
            throw new IllegalStateException(String.format("File must be an image"));
        }
        //3. Grap some metadata from file if any
        Map<String,String> metadata= new HashMap<>();
        metadata.put("Content-Type", itemRequest.getFile().getContentType());
        metadata.put("Content-Length",String.valueOf(itemRequest.getFile().getSize()));
        //5. Store the image in s3 and Update BDD with s3 image link
        String path = String.format("%s", BucketName.PROFILE_FILE.getBucketName());
        String fileName = String.format("%s-%s", itemRequest.getFile().getOriginalFilename(),UUID.randomUUID());
        Item item= new Item();
        try{
            fileStore.save(path,fileName,Optional.of(metadata), itemRequest.getFile().getInputStream());
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            ItemFile file = new ItemFile( fileName, itemRequest.getName(), itemRequest.getDescription(), dateFormat.format(Calendar.getInstance().getTime()));
            item.setFile(file);
        }catch (IOException e){
            throw new IllegalStateException(e);
        }
        item.setName(itemRequest.getName());
        item.setPrice(itemRequest.getPrice());
        item.setImage(itemRequest.getImage());
        item.setDescription(itemRequest.getDescription());
        item.setId(itemRequest.getId());
        item.setDisabled(Boolean.FALSE);
        Optional<Category> category = categoryRepository.findByName(itemRequest.getCategory());
        if(category.isPresent()) item.setCategory(category.get());
        return itemRepository.save(item);
    }

    public void delete(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(()->new com.esgi.jee.apijee.kernel.exceptions.EntityNotFoundException());;
        item.setDisabled(Boolean.TRUE);
        itemRepository.save(item);
    }

    public  List<ItemResponse> resultSearchItem(String searchForUser) {
        List<ItemResponse> items = getItems();
        List<ItemDto> itemsDto = new ArrayList<>();
        items.stream().map(item -> itemsDto.add(itemMapper.mapItemResponseToItemDto(item)));
        List<SearchDto> results = new ArrayList<>();
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
                SearchDto item = new SearchDto(itemsDto.get(k), costs[itemName.length()]);
                results.add(compt, item);
                compt++;
            }
        }
        return searchOrder(results);
    }
    public  List<ItemResponse> searchOrder(List<SearchDto> items) {
        Collections.sort(items, SearchDto.ComparatorDifference);
        List<ItemResponse> itemsReponse = new ArrayList<>();
        items.stream().map(item -> itemsReponse.add(itemMapper.mapSearchDToItemResponse(item)));
        return itemsReponse;
    }
}
