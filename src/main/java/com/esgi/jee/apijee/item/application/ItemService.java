package com.esgi.jee.apijee.item.application;

import com.amazonaws.services.glue.model.EntityNotFoundException;
import com.esgi.jee.apijee.category.domain.Category;
import com.esgi.jee.apijee.category.infrastructure.CategoryRepository;
import com.esgi.jee.apijee.item.domain.Item;
import com.esgi.jee.apijee.item.domain.ItemFile;
import com.esgi.jee.apijee.item.exposition.payload.DownloadItemFileDto;
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
}
