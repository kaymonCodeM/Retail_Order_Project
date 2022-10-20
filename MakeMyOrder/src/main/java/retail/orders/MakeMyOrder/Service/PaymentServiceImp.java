package retail.orders.MakeMyOrder.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retail.orders.MakeMyOrder.Entity.Payment;
import retail.orders.MakeMyOrder.Entity.User;
import retail.orders.MakeMyOrder.Repository.PaymentRepository;

import java.util.Optional;

@Service
public class PaymentServiceImp implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;


    @Override
    public Payment getPaymentById(long paymentId) {
        Optional<Payment> payment = paymentRepository.findById(paymentId);
        if(payment.isPresent()){
            return payment.get();
        }else{
            throw new RuntimeException("Payment by ID was not found: " + paymentId);
        }
    }

}
