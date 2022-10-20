package retail.orders.MakeMyOrder.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retail.orders.MakeMyOrder.Entity.Contact;
import retail.orders.MakeMyOrder.Entity.User;
import retail.orders.MakeMyOrder.Repository.ContactRepository;
import retail.orders.MakeMyOrder.Repository.UserRepository;

import java.util.Optional;

@Service
public class ContactServiceImp implements ContactService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact getContactById(long contactId) {
        Optional<Contact> contact = contactRepository.findById(contactId);
        if(contact.isPresent()){
            return contact.get();
        }else {
            throw new RuntimeException("Contact was not found by id: " + contactId);
        }
    }

    @Override
    public Contact updateContact(Contact contact) {
        Contact newContact = contactRepository.save(contact);
        contact.getUser().setContact(newContact);
        userRepository.save(contact.getUser());
        return contactRepository.save(newContact);
    }


    @Override
    public Contact clearPhoneById(long contactId) {
        Contact contact = getContactById(contactId);
        contact.setPhoneNumber(null);
        contact.getUser().setContact(contact);
        userRepository.save(contact.getUser());
        return contactRepository.save(contact);
    }

    @Override
    public Contact clearEmailById(long contactId) {
        Contact contact = getContactById(contactId);
        contact.setEmail(null);
        contact.getUser().setContact(contact);
        userRepository.save(contact.getUser());
        return contactRepository.save(contact);
    }
}
