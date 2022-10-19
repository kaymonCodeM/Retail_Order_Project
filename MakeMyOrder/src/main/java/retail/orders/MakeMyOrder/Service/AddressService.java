package retail.orders.MakeMyOrder.Service;

import retail.orders.MakeMyOrder.Entity.Address;

public interface AddressService {
    Address updateAddress(Address address);
    Address clearAddress(Address address);
}
