
import com.esgi.jee.apijee.comment.controller.CommentController;
import com.esgi.jee.apijee.comment.service.CommentService;
import com.esgi.jee.apijee.user.application.UserService;
import com.esgi.jee.apijee.user.exposition.UserController;
import com.esgi.jee.apijee.user.exposition.request.RegistrationRequest;
import com.esgi.jee.apijee.user.exposition.request.RoleToUserDtoRequest;
import com.esgi.jee.apijee.user.exposition.response.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {



    private ModelMapper mapper;

    private UserService userService;

    private UserController userControllerTest;

    @BeforeEach
    public void setup() {
        this.userService = Mockito.mock(UserService.class);
        this.mapper = Mockito.mock(ModelMapper.class);
        this.userControllerTest = new UserController(userService,mapper);
    }

    /*@Test
    void createUserTest(){
        MockitoAnnotations.initMocks(this);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        RegistrationRequest user = new RegistrationRequest("kevin","mazure","paris","22 rue pasteur","kevin@gmail.com","0987654321","kevin","jesaispas","jesuislemdp");
        ResponseEntity<Void> result = this.userControllerTest.saveUser(user);
        assertEquals(201, result.getStatusCodeValue());
    }*/

    @Test
    void getUsersTest(){

        UserController userControllerTest = new UserController(userService,mapper);
        ResponseEntity<List<UserResponse>> result = userControllerTest.getUsers();
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    void createRoleTest(){

        UserController userControllerTest = new UserController(userService,mapper);
        RoleToUserDtoRequest role = new RoleToUserDtoRequest(3L,"test");
        ResponseEntity<?> result = userControllerTest.saveRole(role);
        assertEquals(200, result.getStatusCodeValue());
    }
}
