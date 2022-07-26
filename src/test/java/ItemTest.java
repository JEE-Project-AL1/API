import com.esgi.jee.apijee.item.application.ItemService;
import com.esgi.jee.apijee.item.exposition.ItemController;
import com.esgi.jee.apijee.item.exposition.payload.reponse.ItemResponse;
import com.esgi.jee.apijee.item.exposition.payload.request.ItemRequest;
import com.esgi.jee.apijee.user.application.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ItemTest {

    private ItemController itemController;

    @BeforeEach
    public void setup() {
        ItemService itemService = Mockito.mock(ItemService.class);
        this.itemController = new ItemController(itemService);
    }

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

        ResponseEntity<List<ItemResponse>>  result = this.itemController.getItems();
        assertEquals(200, result.getStatusCodeValue());

    }

    @Test
    void getItemByCategory(){

        ResponseEntity<List<ItemResponse>>  result = this.itemController.getItemsByCategory("Food");
        assertEquals(200, result.getStatusCodeValue());

    }

    @Test
    void getItemByName(){
        List<ItemResponse>  result = this.itemController.getItemByName("Viande");
        assertNotNull(result);
    }


}
