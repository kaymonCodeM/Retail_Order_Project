package retail.orders.MakeMyOrder.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long contactId;
    private String email;
    private String phoneNumber;

    @OneToOne(mappedBy = "contact")
    private User user;

    public Contact() {
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Contact:\n" +
                "email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'';
    }
}
