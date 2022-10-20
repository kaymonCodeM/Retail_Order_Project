package retail.orders.MakeMyOrder.Entity;


import javax.persistence.*;

@Entity
@Table(name = "tbl_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addressId;
    private String streetAddress;
    private String Country;
    private String city;
    private String State;
    private String zip;

    @OneToOne(mappedBy = "address")
    private User user;

    public Address() {
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
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
        return "Address:\n" +
                "streetAddress='" + streetAddress + '\'' +
                ", Country='" + Country + '\'' +
                ", city='" + city + '\'' +
                ", State='" + State + '\'' +
                ", zip='" + zip + '\'';
    }
}
