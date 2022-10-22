package retail.orders.MakeMyOrder.Entity;


import javax.persistence.*;

@Entity
@Table(name = "tbl_transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;

    @OneToOne
    @JoinColumn(name = "itemId")
    private Item item;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "orderId")
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
}
