package retail.orders.MakeMyOrder.Entity;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    private Date orderDate;
    private Date deliveryDate;
    private double deliveryCharge;
    private boolean shipped;
    private boolean complete;

    private String orderSummeryUrl;

    @ManyToMany
    @JoinTable(name = "items",joinColumns = @JoinColumn(name = "orderId"),inverseJoinColumns = @JoinColumn(name = "itemId"))
    private List<Item> items;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Order() {
    }

    public Order(Date orderDate, Date deliveryDate, double deliveryCharge, boolean shipped, boolean complete, String orderSummeryUrl, List<Item> items, User user) {
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.deliveryCharge = deliveryCharge;
        this.shipped = shipped;
        this.complete = complete;
        this.orderSummeryUrl = orderSummeryUrl;
        this.items = items;
        this.user = user;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public double getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(double deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public boolean isShipped() {
        return shipped;
    }

    public void setShipped(boolean shipped) {
        this.shipped = shipped;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String getOrderSummeryUrl() {
        return orderSummeryUrl;
    }

    public void setOrderSummeryUrl(String orderSummeryUrl) {
        this.orderSummeryUrl = orderSummeryUrl;
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
