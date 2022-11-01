package retail.orders.MakeMyOrder.Entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "tbl_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private long addressId;
    @Column(updatable = false)
    private String streetAddress;
    @Column(updatable = false)
    private String country;
    @Column(updatable = false)
    private String city;
    @Column(updatable = false)
    private String state;
    @Column(updatable = false)
    private String zip;

    @OneToOne(mappedBy = "address")
    @JsonIgnoreProperties("address")
    private Order order;

    public Address(String streetAddress, String country, String city, String state, String zip) {
        this.streetAddress = streetAddress;
        this.country = country;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

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
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
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
        return "Address{" +
                "addressId=" + addressId +
                ", streetAddress='" + streetAddress + '\'' +
                ", Country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", State='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", order=" + order +
                '}';
    }
}
