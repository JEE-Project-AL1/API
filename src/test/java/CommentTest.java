
import com.esgi.jee.apijee.comment.controller.CommentController;
import com.esgi.jee.apijee.comment.dto.CommentDto;
import com.esgi.jee.apijee.comment.service.CommentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class CommentTest {


    @Test
    public void createCommentTest(){
        CommentService commentService = Mockito.mock(CommentService.class);
        CommentController commentControllerTest = new CommentController(commentService);
        CommentDto commentDto = new CommentDto(89L,1L,4L,"kevin","je suis le commentaire",5);

        ResponseEntity<CommentDto> result = commentControllerTest.createComment(commentDto);

        assertEquals(201,result.getStatusCodeValue());
    }

    @Test
    public void getCommentByIdTest(){
        CommentService commentService = Mockito.mock(CommentService.class);
        CommentController commentControllerTest = new CommentController(commentService);
        CommentDto commentDto = new CommentDto(189L,1L,3L,"kevin","je suis le commentaire",3);

        ResponseEntity<CommentDto> result = commentControllerTest.getCommentById(commentDto.getId());

        assertEquals(200,result.getStatusCodeValue());
    }

    @Test
    public void updateCommentTest(){
        CommentService commentService = Mockito.mock(CommentService.class);
        CommentController commentControllerTest = new CommentController(commentService);
        CommentDto commentDto = new CommentDto(9L,1L,3L,"kevin","je suis le commentaire",3);

        //ResponseEntity<CommentDto> commentCreate = commentControllerTest.createComment(commentDto);

        CommentDto commentDtoUpdate = new CommentDto(9L,1L,3L,"kevin","je suis le commentaire different",5);

        ResponseEntity<CommentDto> result = commentControllerTest.updateComment(commentDtoUpdate.getId(),commentDtoUpdate);

        assertEquals(200,result.getStatusCodeValue());
    }

    @Test
    public void getCommentByItemIdTest(){
        CommentService commentService = Mockito.mock(CommentService.class);
        CommentController commentControllerTest = new CommentController(commentService);
        List<CommentDto> result = commentControllerTest.getCommentsByItemId(2L);

        assertNotNull(result);
    }

    @Test
    public void getCommentByUserIdTest(){
        CommentService commentService = Mockito.mock(CommentService.class);
        CommentController commentControllerTest = new CommentController(commentService);
        List<CommentDto> result = commentControllerTest.getCommentsByUserId(9L);

        assertNotNull(result);
    }


    @Test
    public void getAllCommentsTest(){
        CommentService commentService = Mockito.mock(CommentService.class);
        CommentController commentControllerTest = new CommentController(commentService);
        List<CommentDto> result = commentControllerTest.getAllComments();

        assertNotNull(result);
    }

}
