package retail.orders.MakeMyOrder.Service;

import retail.orders.MakeMyOrder.Entity.Contact;

public interface ContactService {
    Contact updatePhone(String phoneNumber,Contact contact);
    Contact updateEmail(String email,Contact contact);
    Contact clearPhone(Contact contact);
    Contact clearEmail(Contact contact);
}
