import com.esgi.jee.apijee.category.application.CategoryService;
import com.esgi.jee.apijee.category.domain.Category;
import com.esgi.jee.apijee.category.infrastructure.CategoryRepository;
import com.esgi.jee.apijee.item.application.ItemService;
import com.esgi.jee.apijee.item.exposition.ItemController;
import com.esgi.jee.apijee.user.application.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CategoryTest {

    private CategoryService categoryService;

    @BeforeEach
    public void setup() {
        CategoryRepository categoryRepository = Mockito.mock(CategoryRepository.class);
        this.categoryService = new CategoryService(categoryRepository);
    }

    /*@Test
    void createCategoryTest(){
        CategoryRepository categoryRepository = Mockito.mock(CategoryRepository.class);
        CategoryService categoryService = new CategoryService(categoryRepository);
        Category category = categoryService.saveCategory(new Category(12345L,"test2"));
        assertEquals("test2",category.getName());
    }*/

    @Test
    void getCategorysTest(){

        List<Category> results = this.categoryService.getCategorys();
        assertNotNull(results);
    }
}
