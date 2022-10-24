package retail.orders.MakeMyOrder.Entity;


import com.fasterxml.jackson.annotation.*;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "tbl_transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private long transactionId;

    @ManyToOne
    @JoinColumn(name = "itemId",updatable = false)
    private Item item;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "orderId",updatable = false)
    private Order order;

    public Transaction(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Transaction() {
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", item=" + item +
                ", quantity=" + quantity +
                ", order=" + order +
                '}';
    }
}
