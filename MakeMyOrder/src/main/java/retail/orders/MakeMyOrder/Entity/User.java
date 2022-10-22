package retail.orders.MakeMyOrder.Entity;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String username;
    private String password;
    private boolean active = true;
    private String roles;

    @OneToMany(mappedBy = "user")
    @Value("#{new java.util.ArrayList()}")
    private Set<Payment> payments;


    @OneToOne
    @JoinColumn(name = "contactId")
    private Contact contact;

    @OneToOne
    @JoinColumn(name = "addressId")
    private Address address;

    @OneToMany(mappedBy = "user")
    @Value("#{new java.util.ArrayList()}")
    private Set<Order> orders;

    public User() {
    }

    public User(String username, String password, String roles, Contact contact, Address address) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.contact = contact;
        this.address = address;
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

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", roles='" + roles + '\'' +
                ", payments=" + payments +
                ", contact=" + contact +
                ", address=" + address +
                ", orders=" + orders +
                '}';
    }
}
