package retail.orders.MakeMyOrder.Service;

import retail.orders.MakeMyOrder.Entity.Payment;
import retail.orders.MakeMyOrder.Entity.User;

public interface PaymentService {
    Payment getPaymentById(long paymentId);

    Payment addPayment(long userId,long paymentId);

    Payment updatePayment(long userId,Payment payment);
    String removePaymentById(long paymentId);
}
