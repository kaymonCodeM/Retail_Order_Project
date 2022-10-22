package retail.orders.MakeMyOrder.Service;

import retail.orders.MakeMyOrder.Entity.User;

import java.util.List;

public interface MyUserDetailsService {

    List<User> findAllUsers();
    User getUserByUsername(String username);
    User addUser(User user);

    User updateUser(User user);
    User getUserById(long userId);

    String deleteUserById(long userId);
    //PasswordEncoder getEncoder();
}
