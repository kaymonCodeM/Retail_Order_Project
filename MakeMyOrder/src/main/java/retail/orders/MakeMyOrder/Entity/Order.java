package retail.orders.MakeMyOrder.Entity;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "tbl_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    private LocalDate orderDate;

    @Value("#{null}")
    private LocalDate deliveryDate;

    @Value("#{false}")
    private boolean canceled;
    @Value("#{false}")
    private boolean shipped;
    @Value("#{false}")
    private boolean complete;

    private String orderSummeryUrl;

    private double totalCost;

    @OneToMany(mappedBy = "order")
    private List<Transaction> transactions;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Order() {
    }

    public Order(LocalDate orderDate, List<Transaction> transactions, User user) {
        this.orderDate = orderDate;
        this.transactions = transactions;
        this.user = user;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }


    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
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

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", canceled=" + canceled +
                ", shipped=" + shipped +
                ", complete=" + complete +
                ", orderSummeryUrl='" + orderSummeryUrl + '\'' +
                ", transactions=" + transactions +
                ", user=" + user +
                '}';
    }
}
