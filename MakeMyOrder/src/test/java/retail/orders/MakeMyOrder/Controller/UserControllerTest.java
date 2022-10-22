package retail.orders.MakeMyOrder.Controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {

    @Autowired
    UserController userController;


    @Test
    void testAddUser(){
        String s =  userController.addUser("Shawn","pass","ROLE_USER");
        assertEquals(s,"successful new user","Test to add User failed");
    }

    @Test
    void deleteUser(){
        String userId = "1";
        String s =  userController.deleteUser(userId);
        assertEquals(s,"User Deleted by Id: " + userId,"test to delete user failed");
    }
}