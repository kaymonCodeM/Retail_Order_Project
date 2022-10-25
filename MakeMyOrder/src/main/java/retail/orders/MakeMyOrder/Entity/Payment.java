package retail.orders.MakeMyOrder.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private long paymentId;

    @Column(updatable = false)
    private String cardHolder;

    @Column(updatable = false)
    private String cardNumber;

    @Column(updatable = false)
    private LocalDate expirationDate;

    @Column(updatable = false)
    private int cvv;

    @Column(updatable = false)
    private String zip;


    @OneToOne(mappedBy = "payment")
    @JsonIgnoreProperties("payment")
    private Order order;

    public Payment() {
    }

    public Payment(String cardHolder, String cardNumber, LocalDate expirationDate, int cvv, String zip) {
        this.cardHolder = cardHolder;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.zip = zip;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", cardHolder='" + cardHolder + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", expirationDate=" + expirationDate +
                ", cvv=" + cvv +
                ", zip='" + zip + '\'' +
                ", order=" + order +
                '}';
    }
}
