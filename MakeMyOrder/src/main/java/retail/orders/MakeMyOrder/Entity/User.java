package retail.orders.MakeMyOrder.Entity;

import java.util.List;

public class User {
    private long userId;
    private String username;
    private String password;
    private Contact contact;
    private Address address;
    private Cart cart;
    private List<Order> orders;
    private boolean active;
    private List<String> roles;
}
