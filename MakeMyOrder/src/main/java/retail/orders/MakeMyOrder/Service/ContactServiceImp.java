package retail.orders.MakeMyOrder.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retail.orders.MakeMyOrder.Entity.Contact;
import retail.orders.MakeMyOrder.Repository.ContactRepository;

@Service
public class ContactServiceImp implements ContactService{

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact updatePhone(String phoneNumber, Contact contact) {
        contact.setPhoneNumber(phoneNumber);
        return contactRepository.save(contact);
    }

    @Override
    public Contact updateEmail(String email, Contact contact) {
        contact.setEmail(email);
        return contactRepository.save(contact);
    }

    @Override
    public Contact clearPhone(Contact contact) {
        contact.setPhoneNumber("");
        return contactRepository.save(contact);
    }

    @Override
    public Contact clearEmail(Contact contact) {
        contact.setEmail("");
        return contactRepository.save(contact);
    }
}
