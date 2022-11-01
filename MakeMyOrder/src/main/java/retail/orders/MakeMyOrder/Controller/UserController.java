package retail.orders.MakeMyOrder.Controller;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import retail.orders.MakeMyOrder.Entity.User;
import retail.orders.MakeMyOrder.MakeMyOrderApplication;
import retail.orders.MakeMyOrder.Service.MyUserDetailsService;
import retail.orders.MakeMyOrder.Service.TokenService;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private TokenService tokenService;


    private Logger log = LoggerFactory.getLogger(MakeMyOrderApplication.class);

    @GetMapping("/user/all")
    List<User> getAllUsers(){
        return myUserDetailsService.findAllUsers();
    }
    @PostMapping("/user/create")
    String addUser(@RequestBody User user){

        return myUserDetailsService.addUser(user);
    }

    @DeleteMapping("/user/{userId}")
    public String deleteUser(@PathVariable String userId){
        return myUserDetailsService.deleteUserById(Long.parseLong(userId));
    }

    @GetMapping("/user/id/{userId}")
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

    @PostMapping("/token")
    public String token(Authentication authentication) throws JSONException {
        return tokenService.generateToken(authentication);
    }


}
