package retail.orders.MakeMyOrder.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String username;
    private String password;
    private boolean active;
    private String roles;

    @OneToMany(mappedBy = "user")
    private List<Payment> payments;


    @OneToOne
    @JoinColumn(name = "contactId")
    private Contact contact;

    @OneToOne
    @JoinColumn(name = "addressId")
    private Address address;

    @OneToMany(mappedBy = "user")
     private List<Order> orders;

    public User() {
    }

    public User(String username, String password, String roles, List<Payment> payments, Contact contact, Address address, List<Order> orders) {
        this.username = username;
        this.password = password;
        this.active = true;
        this.roles = roles;
        this.payments = payments;
        this.contact = contact;
        this.address = address;
        this.orders = orders;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User:\n" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", roles='" + roles + '\'' + "\n\n" +
                ", " + payments + "\n\n" +
                ", " + contact + "\n\n" +
                ", " + address + "\n\n" +
                ", " + orders;
    }
}
