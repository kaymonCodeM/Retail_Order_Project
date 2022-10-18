package retail.orders.MakeMyOrder.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemId;
    private String type;
    private String imageUrl;
    private String description;
    private int quantity;
    private double price;

    @ManyToMany
    @JoinTable(name = "orders",joinColumns = @JoinColumn(name = "itemId"),inverseJoinColumns = @JoinColumn(name = "orderId"))
    private List<Order> orders;

    @ManyToMany
    @JoinTable(name = "carts",joinColumns = @JoinColumn(name = "itemId"),inverseJoinColumns = @JoinColumn(name = "cartId"))
    private List<Cart> carts;

    public Item() {
    }

    public Item(long itemId, String type, String imageUrl, String description, int quantity, double price) {
        this.itemId = itemId;
        this.type = type;
        this.imageUrl = imageUrl;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }
}
