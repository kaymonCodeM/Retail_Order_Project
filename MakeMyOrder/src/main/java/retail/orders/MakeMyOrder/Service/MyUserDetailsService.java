package retail.orders.MakeMyOrder.Service;

import retail.orders.MakeMyOrder.Entity.User;

import java.util.List;

public interface MyUserDetailsService {

    List<User> findAllUsers();
    User getUserByUsername(String username);
    String addUser(String username,String password, String roles);

    String updatePassword(long userId,String password);

    String updateUsername(long userId,String Username);
    String deleteUser(long userId);
    //PasswordEncoder getEncoder();
}
