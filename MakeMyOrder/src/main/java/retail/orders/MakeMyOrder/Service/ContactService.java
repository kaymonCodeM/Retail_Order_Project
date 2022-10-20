package retail.orders.MakeMyOrder.Service;

import retail.orders.MakeMyOrder.Entity.Contact;

public interface ContactService {

    Contact getContactById(long contactId);
    Contact updateContact(Contact contact);
    Contact clearPhoneById(long contactId);
    Contact clearEmailById(long contactId);
}
