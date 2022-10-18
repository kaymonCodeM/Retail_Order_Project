package retail.orders.MakeMyOrder.Entity;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;

    @ManyToMany(mappedBy = "carts")
    @Value("${new java.util.ArrayList()}")
    private List<Item> items;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    public Cart() {
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
