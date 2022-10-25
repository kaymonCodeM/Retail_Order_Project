package retail.orders.MakeMyOrder.Service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import retail.orders.MakeMyOrder.Entity.User;

import java.util.List;

public interface MyUserDetailsService extends UserDetailsService {

    List<User> findAllUsers();
    User getUserByUsername(String username);
    String addUser(User user);

    User updateUser(User user);
    User getUserById(long userId);

    String deleteUserById(long userId);

    PasswordEncoder getEncoder();
}
