package retail.orders.MakeMyOrder.Service;

import retail.orders.MakeMyOrder.Entity.User;

import java.util.List;

public interface MyUserDetailsService {

    List<User> findAllUsers();
    User getUserByUsername(String username);
    String addUser(String username,String password, String roles);

    String updatePasswordById(long userId,String password);

    String updateUsernameById(long userId,String Username);
    String deleteUserById(long userId);
    //PasswordEncoder getEncoder();
}
