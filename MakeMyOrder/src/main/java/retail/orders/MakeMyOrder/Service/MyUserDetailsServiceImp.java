package retail.orders.MakeMyOrder.Service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retail.orders.MakeMyOrder.Entity.*;
import retail.orders.MakeMyOrder.Repository.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MyUserDetailsServiceImp implements MyUserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private Logger log;

    //private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> user = this.userRepository.findByUsername(username);
        return user.orElse(null);
    }

    @Override
    public User addUser(User user) {
        Optional<User> u = userRepository.findByUsername(user.getUsername());
        if(u.isEmpty()){
            log.debug("User is now saved successfully");
            return userRepository.save(user);
        }
        log.error("User was not created");
        return null;
    }

    @Override
    public User updateUser(User user) {
        User u = getUserByUsername(user.getUsername());
        if(u==null){
            log.debug("User id: "+ user.getUserId() + " is now updated");
            return userRepository.save(user);
        }else if(user.getUserId()==u.getUserId()){
            log.debug("User id: "+ user.getUserId() + " is now updated");
            return userRepository.save(user);
        }
        log.error("User name: "+ u.getUsername() + " has already been used");
        return null;
    }

    @Override
    public User getUserById(long userId) {
        Optional<User> user = this.userRepository.findById(userId);
        return user.orElse(null);
    }


    @Override
    public String deleteUserById(long userId) {
        List<Order> orders = orderService.findOrdersByUserId(userId);
        for (Order o: orders){
            orderService.deleteOrderById(o.getOrderId());
        }
        userRepository.deleteById(userId);

        log.debug("User id: "+ userId + " deleted successfully");
        return "User deleted successfully by Id: " + userId;
    }
//
//    @Override
//    public PasswordEncoder getEncoder() {
//        return this.passwordEncoder;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByUsername(username)
//                .map(MyUserDetails::new)
//                .orElseThrow(()->new UsernameNotFoundException("Username not found: " + username));
//    }
}
