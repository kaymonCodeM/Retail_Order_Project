package retail.orders.MakeMyOrder.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retail.orders.MakeMyOrder.Entity.Address;
import retail.orders.MakeMyOrder.Repository.AddressRepository;
import retail.orders.MakeMyOrder.Repository.UserRepository;

import java.util.Optional;

@Service
public class AddressServiceImp implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Address getAddressById(long addressId) {
        Optional<Address> address = addressRepository.findById(addressId);
        if(address.isPresent()){
            return address.get();
        }else {
            throw new RuntimeException("Address was not found by id: "+addressId);
        }
    }

    @Override
    public Address updateAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address clearAddressById(long addressId) {
        Address address = getAddressById(addressId);
        address.setStreetAddress(null);
        address.setCity(null);
        address.setCountry(null);
        address.setState(null);
        address.setZip(null);

        return addressRepository.save(address);
    }
}
