package retail.orders.MakeMyOrder.Controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import retail.orders.MakeMyOrder.Entity.User;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {

    @Autowired
    UserController userController;


    @Test
    void addUser(){
        User user = new User("Shawn","pass",true,"ROLE_USER");
        User result =  userController.addUser(user);
        assertNotNull(result,"Test to add User failed");
    }

    @Test
    void deleteUser(){
        String userId = "4";
        String s =  userController.deleteUser(userId);
        assertEquals(s,"User deleted successfully by Id: " + userId,"test to delete user failed");
    }

    @Test
    void updateUser(){
        User user = new User("Hannah","pass",true,"ROLE_USER");
        user.setUserId(4);
        User result = userController.updateUser(user);
        assertNotNull(result,"Test to add User failed");
    }
}