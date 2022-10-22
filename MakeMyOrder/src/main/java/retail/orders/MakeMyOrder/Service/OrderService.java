package retail.orders.MakeMyOrder.Service;

import retail.orders.MakeMyOrder.Entity.*;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    List<Order> findOrders();

    List<Order> findOrdersByUserId(long userId);

    Order saveOrder(Order order);
    String cancelOrderById(long order);

    Order findOrderById(long orderId);

    String deleteOrderById(long orderId);

    String setShippedById(long orderId);

    String setDeliveredById(long orderId);

    Order updateOrder(Order Order);

    Address findAddressByOrderId(long orderId);
    Contact findContactByOrderId(long orderId);
    Payment findPaymentByOrderId(long orderId);

    User findUserByOrderId(long orderId);

    List<Transaction> findTransactionsByOrderId(long orderId);

    List<Transaction> findAllTransactions();
}
