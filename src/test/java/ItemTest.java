import com.esgi.jee.apijee.item.application.ItemService;
import com.esgi.jee.apijee.item.exposition.ItemController;
import com.esgi.jee.apijee.item.exposition.payload.reponse.ItemResponse;
import com.esgi.jee.apijee.item.exposition.payload.request.ItemRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ItemTest {
    /*@Test
    void createItemTest(){
        ItemService itemService = Mockito.mock(ItemService.class);
        ItemController itemController = new ItemController(itemService);
        MultipartFile file = null;
        ItemRequest item = new ItemRequest(file,"Voiture","jolie voiture bleu", 15000.0,"transport");
        ResponseEntity<Void>  result = itemController.saveItem(item,file);
        assertEquals(201, result.getStatusCodeValue());

    }*/

    @Test
    void getItemsTest(){
        ItemService itemService = Mockito.mock(ItemService.class);
        ItemController itemController = new ItemController(itemService);
        ResponseEntity<List<ItemResponse>>  result = itemController.getItems();
        assertEquals(200, result.getStatusCodeValue());

    }

    @Test
    void getItemByCategory(){
        ItemService itemService = Mockito.mock(ItemService.class);
        ItemController itemController = new ItemController(itemService);
        ResponseEntity<List<ItemResponse>>  result = itemController.getItemsByCategory("Food");
        assertEquals(200, result.getStatusCodeValue());

    }

    @Test
    void getItemByName(){
        ItemService itemService = Mockito.mock(ItemService.class);
        ItemController itemController = new ItemController(itemService);
        List<ItemResponse>  result = itemController.getItemByName("Viande");
        assertNotNull(result);
    }


}
