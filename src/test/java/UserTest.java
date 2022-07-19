
import com.esgi.jee.apijee.user.application.UserService;
import com.esgi.jee.apijee.user.exposition.UserController;
import com.esgi.jee.apijee.user.exposition.request.RoleToUserDtoRequest;
import com.esgi.jee.apijee.user.exposition.response.UserResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {


    /*@Test
    void createUserTest(){
        UserService userService = Mockito.mock(UserService.class);
        ModelMapper mapper = Mockito.mock(ModelMapper.class);
        UserController userControllerTest = new UserController(userService,mapper);
        RegistrationRequest user = new RegistrationRequest("kevin","mazure","paris","22 rue pasteur","kevin@gmail.com","0987654321","kevin","jesaispas","jesuislemdp");
        ResponseEntity<Void> result = userControllerTest.saveUser(user);
        assertEquals(201, result.getStatusCodeValue());
    }*/

    @Test
    void getUsersTest(){
        UserService userService = Mockito.mock(UserService.class);
        ModelMapper mapper = Mockito.mock(ModelMapper.class);
        UserController userControllerTest = new UserController(userService,mapper);
        ResponseEntity<List<UserResponse>> result = userControllerTest.getUsers();
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    void createRoleTest(){
        UserService userService = Mockito.mock(UserService.class);
        ModelMapper mapper = Mockito.mock(ModelMapper.class);
        UserController userControllerTest = new UserController(userService,mapper);
        RoleToUserDtoRequest role = new RoleToUserDtoRequest(3L,"test");
        ResponseEntity<?> result = userControllerTest.saveRole(role);
        assertEquals(200, result.getStatusCodeValue());
    }
}
