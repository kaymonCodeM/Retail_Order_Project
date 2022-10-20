package retail.orders.MakeMyOrder.Service;

import retail.orders.MakeMyOrder.Entity.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface OrderService {

    List<Order> findOrders();

    Order addOrder(long userId, Map<Item,Integer> items, Contact contact, Address address, Payment payment);
    String cancelOrderById(long order);

    Order findOrderById(long orderId);

    String deleteOrderById(long orderId);

    String setShippedById(long orderId);

    String setDeliveredById(long orderId, LocalDate deliveredDate);
}
