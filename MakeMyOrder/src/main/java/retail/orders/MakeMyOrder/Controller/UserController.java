package retail.orders.MakeMyOrder.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import retail.orders.MakeMyOrder.Entity.User;
import retail.orders.MakeMyOrder.Service.MyUserDetailsService;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @GetMapping("/user/all")
    List<User> getAllUsers(){
        return myUserDetailsService.findAllUsers();
    }
    @PostMapping("/user/create")
    User addUser(@RequestBody User user){
        return myUserDetailsService.addUser(user);
    }

    @DeleteMapping("/user/{userId}")
    public String deleteUser(@PathVariable String userId){
        return myUserDetailsService.deleteUserById(Long.parseLong(userId));
    }

    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable String userId){
        return myUserDetailsService.getUserById(Long.parseLong(userId));
    }

    @GetMapping("/user/{username}")
    public User getUserByUsername(@PathVariable String username){
        return myUserDetailsService.getUserByUsername(username);
    }

    @PutMapping("/user/update")
    public User updateUser(@RequestBody User user){
        return myUserDetailsService.updateUser(user);
    }


}
