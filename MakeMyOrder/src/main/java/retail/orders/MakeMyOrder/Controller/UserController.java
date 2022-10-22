package retail.orders.MakeMyOrder.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import retail.orders.MakeMyOrder.Entity.User;
import retail.orders.MakeMyOrder.Service.MyUserDetailsService;

@RestController
public class UserController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @PostMapping("/newUser")
    String addUser(@RequestParam String username,@RequestParam String password, @RequestParam String roles){
        return myUserDetailsService.addUser(username,password,roles);
    }

    @DeleteMapping("/deleteUser/{userID}")
    public String deleteUser(@PathVariable String userID){
        return myUserDetailsService.deleteUserById(Long.parseLong(userID));
    }

}
