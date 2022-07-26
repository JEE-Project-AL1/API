
import com.esgi.jee.apijee.comment.controller.CommentController;
import com.esgi.jee.apijee.comment.dto.CommentDto;
import com.esgi.jee.apijee.comment.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class CommentTest {
    CommentController commentControllerTest;

    @BeforeEach
    public void setup() {
        CommentService commentService = Mockito.mock(CommentService.class);
        this.commentControllerTest = new CommentController(commentService);
    }


    @Test
    public void createCommentTest(){

        CommentDto commentDto = new CommentDto(89L,1L,4L,"kevin","je suis le commentaire",5);

        ResponseEntity<CommentDto> result = this.commentControllerTest.createComment(commentDto);

        assertEquals(201,result.getStatusCodeValue());

    }

    @Test
    public void getCommentByIdTest(){

        CommentDto commentDto = new CommentDto(189L,1L,3L,"kevin","je suis le commentaire",3);

        ResponseEntity<CommentDto> result = this.commentControllerTest.getCommentById(commentDto.getId());

        assertEquals(200,result.getStatusCodeValue());
    }

    @Test
    public void updateCommentTest(){
        CommentDto commentDto = new CommentDto(9L,1L,3L,"kevin","je suis le commentaire",3);

        CommentDto commentDtoUpdate = new CommentDto(9L,1L,3L,"kevin","je suis le commentaire different",5);

        ResponseEntity<CommentDto> result = this.commentControllerTest.updateComment(commentDtoUpdate.getId(),commentDtoUpdate);

        assertEquals(200,result.getStatusCodeValue());
    }

    @Test
    public void getCommentByItemIdTest(){
        List<CommentDto> result = this.commentControllerTest.getCommentsByItemId(2L);

        assertNotNull(result);
    }

    @Test
    public void getCommentByUserIdTest(){
        List<CommentDto> result = this.commentControllerTest.getCommentsByUserId(9L);

        assertNotNull(result);
    }


    @Test
    public void getAllCommentsTest(){

        List<CommentDto> result = this.commentControllerTest.getAllComments();

        assertNotNull(result);
    }

}
