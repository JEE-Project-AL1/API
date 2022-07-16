package com.esgi.jee.apijee.item.exposition;
import com.esgi.jee.apijee.item.application.ItemService;
import com.esgi.jee.apijee.item.domain.Item;
import com.esgi.jee.apijee.item.exposition.payload.reponse.ItemResponse;
import com.esgi.jee.apijee.item.exposition.payload.request.ItemRequest;
import com.esgi.jee.apijee.item.exposition.payload.request.ItemUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@Slf4j
@RestController
@RequestMapping( value = "/api/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Void> saveItem(@RequestPart("details") ItemRequest itemRequest,
                                         @ModelAttribute MultipartFile file){
        itemRequest.setFile(file);
        Item item = itemService.saveItem(itemRequest);
        URI location = URI.create(
                ServletUriComponentsBuilder.fromCurrentRequest().build().toUri() + "/" + item.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateItem(@RequestPart("details") ItemUpdateRequest itemRequest,
                                         @ModelAttribute MultipartFile file){
        itemRequest.setFile(file);
        Item item = itemService.updateItem(itemRequest);
        URI location = URI.create(
                ServletUriComponentsBuilder.fromCurrentRequest().build().toUri() + "/" + item.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<ItemResponse>> getItems(){
        return ResponseEntity.ok().body(itemService.getItems());
    }

    @GetMapping("/search/{name}")
    public List<ItemResponse> getItemByName(@PathVariable(name="name") String name){
        List<ItemResponse> search = this.itemService.resultSearchItem(name);
        return search;
    }
    @GetMapping(path ="/{id}")
    public ResponseEntity<ItemResponse> getItemById(@PathVariable Long id){
        return ResponseEntity.ok().body(itemService.getItemById(id));
    }
    @GetMapping(path ="/category/{category}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ItemResponse>> getItemsByCategory(@PathVariable String category){
        return ResponseEntity.ok().body(itemService.getItemsByCategory(category));
    }

    @DeleteMapping(path ="/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id){
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
