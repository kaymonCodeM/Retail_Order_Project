package retail.orders.MakeMyOrder.Componets;

import org.springframework.stereotype.Component;
import retail.orders.MakeMyOrder.Entity.Address;
import retail.orders.MakeMyOrder.Entity.Contact;
import retail.orders.MakeMyOrder.Entity.Payment;
import retail.orders.MakeMyOrder.Entity.Transaction;

import javax.persistence.Entity;
import java.util.List;
import java.util.Set;

@Component
public class UpdateOrderRequest {

    private long orderId;
    private List<Transaction> transactions;
    private Address address;
    private Contact contact;
    private Payment payment;

    public UpdateOrderRequest(long orderId, List<Transaction> transactions, Address address, Contact contact, Payment payment) {
        this.orderId = orderId;
        this.transactions = transactions;
        this.address = address;
        this.contact = contact;
        this.payment = payment;
    }

    public UpdateOrderRequest() {
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
