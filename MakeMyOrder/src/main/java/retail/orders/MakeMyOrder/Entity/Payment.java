package retail.orders.MakeMyOrder.Entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentId")
    private long paymentId;

    @Column(name = "cardHolder")
    private String cardHolder;

    @Column(name = "cardNumber")
    private String cardNumber;
    @Column(name = "expirationDate")
    private LocalDate expirationDate;
    @Column(name = "cvv")
    private int cvv;

    @Column(name = "zip")
    private String zip;


    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", cardHolder='" + cardHolder + '\'' +
                ", cardNumber=" + cardNumber +
                ", expirationDate=" + expirationDate +
                ", cvv=" + cvv +
                ", zip='" + zip + '\'' +
                ", user=" + user +
                '}';
    }
}
