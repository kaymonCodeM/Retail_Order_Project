package retail.orders.MakeMyOrder.Service;

import retail.orders.MakeMyOrder.Entity.*;

import java.util.List;
import java.util.Map;

public interface OrderService {

    List<Order> findOrders();

    Order addOrder(User user, Map<Item,Integer> items, Contact contact, Address address, Payment payment);
    String cancelOrderById(Order order);

    Order findOrderById(long orderId);

    String deleteOrder(Order order);
}
