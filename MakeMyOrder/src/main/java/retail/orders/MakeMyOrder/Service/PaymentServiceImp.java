package retail.orders.MakeMyOrder.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retail.orders.MakeMyOrder.Entity.Payment;
import retail.orders.MakeMyOrder.Entity.User;
import retail.orders.MakeMyOrder.Repository.PaymentRepository;
import retail.orders.MakeMyOrder.Repository.UserRepository;

import java.util.Optional;

@Service
public class PaymentServiceImp implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Payment getPaymentById(long paymentId) {
        Optional<Payment> payment = paymentRepository.findById(paymentId);
        if(payment.isPresent()){
            return payment.get();
        }else{
            throw new RuntimeException("Payment by ID was not found: " + paymentId);
        }
    }

    @Override
    public Payment addPayment(long userId, Payment payment) {
        User user;
        Optional<User> u = userRepository.findById(userId);
        if(u.isPresent()){
            user = u.get();
        }else{
            throw new RuntimeException("User by Id was not found: " + userId);
        }
        payment.setUser(user);
        Payment savePayment = paymentRepository.save(payment);
        user.getPayments().add(savePayment);
        userRepository.save(user);

        return savePayment;
    }

    @Override
    public Payment updatePayment(Payment payment) {
        User user = payment.getUser();

        for(Payment p:user.getPayments()){
            if(p.getPaymentId()==payment.getPaymentId()){
                user.getPayments().remove(p);
                break;
            }
        }

        user.getPayments().add(payment);
        userRepository.save(user);
        return paymentRepository.save(payment);
    }

    @Override
    public String removePaymentById(long paymentId) {
        Payment payment = getPaymentById(paymentId);
        User user = payment.getUser();
        for (Payment p : user.getPayments()){
            if(p.getPaymentId()==paymentId){
                user.getPayments().remove(payment);
            }
        }
        userRepository.save(user);
        paymentRepository.deleteById(paymentId);
        return "Removed payment successfully";
    }

}
