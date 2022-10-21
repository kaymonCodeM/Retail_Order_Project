package retail.orders.MakeMyOrder.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retail.orders.MakeMyOrder.Entity.*;
import retail.orders.MakeMyOrder.Repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsServiceImp implements MyUserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;


    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private PaymentRepository paymentRepository;


    //private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> user = this.userRepository.findByUsername(username);
        if(user.isPresent()){
            return user.get();
        }else {
            throw new RuntimeException("Username not found: " + username);
        }
    }

    @Override
    public String addUser(String username,String password, String roles) {
        Optional<User> innerUser = userRepository.findByUsername(username);
        if(innerUser.isEmpty()) {

            Address address = new Address();
            Contact contact = new Contact();


            Address saveAddress = addressRepository.save(address);
            Contact saveContact = contactRepository.save(contact);

            //String passwordEncode = passwordEncoder.encode(password);

            User user = new User(username,password,roles,saveContact,saveAddress);

            saveAddress.setUser(user);
            saveContact.setUser(user);

            userRepository.save(user);
        }else{
            return "User is already in the system";
        }
        return "successful new user";
    }

    @Override
    public String updatePasswordById(long userId,String password) {
        //String passwordEncode = passwordEncoder.encode(password);
        Optional<User> innerUser = userRepository.findById(userId);
        if(innerUser.isPresent()){
            User user = innerUser.get();
            user.setPassword(password);
            userRepository.save(user);
        }else {
            return "User was not found by Id: " + userId;
        }
        return "User password updated successfully";
    }

    @Override
    public String updateUsernameById(long userId, String username) {
        Optional<User> updateUser = userRepository.findByUsername(username);
        if(updateUser.isEmpty()) {
            Optional<User> innerUser = userRepository.findById(userId);
            if (innerUser.isPresent()) {
                User user = innerUser.get();
                user.setUsername(username);
                userRepository.save(user);
            } else {
                return "User was not found by Id: " + userId;
            }
        }else{
            return "User is already in the system";
        }
        return "Username updated successfully";
    }

    @Override
    public String deleteUserById(long userId) {
        Optional<User> innerUser = userRepository.findById(userId);
        if(innerUser.isPresent()){
            User user = innerUser.get();

            for (Payment payment: user.getPayments()){
                paymentRepository.delete(payment);
            }

            user.getPayments().clear();

            user.getOrders().clear();

            addressRepository.delete(user.getAddress());
            contactRepository.delete(user.getContact());

            this.userRepository.delete(user);
        }else {
            return "User is not found id: " +userId;
        }
        return "User Deleted by Id: " + userId;
    }
//
//    @Override
//    public PasswordEncoder getEncoder() {
//        return this.passwordEncoder;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByUsername(username)
//                .map(MyUserDetails::new)
//                .orElseThrow(()->new UsernameNotFoundException("Username not found: " + username));
//    }
}
