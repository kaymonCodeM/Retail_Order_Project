package retail.orders.MakeMyOrder.Service;

import retail.orders.MakeMyOrder.Entity.Address;

public interface AddressService {

    Address getAddressById(long addressId);
    Address updateAddress(Address address);
    Address clearAddressById(long addressId);
}
