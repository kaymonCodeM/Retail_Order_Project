package retail.orders.MakeMyOrder.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retail.orders.MakeMyOrder.Entity.Address;
import retail.orders.MakeMyOrder.Repository.AddressRepository;

import java.util.Optional;

@Service
public class AddressServiceImp implements AddressService {

    @Autowired
    private AddressRepository addressRepository;


    @Override
    public Address updateAddress(Address address) {
        Address updateAddress = addressRepository.save(address);
        return addressRepository.save(address);
    }

    @Override
    public Address clearAddress(Address address) {
        address.setStreetAddress("");
        address.setCity("");
        address.setCountry("");
        address.setState("");
        address.setZip("");
        return addressRepository.save(address);
    }
}
