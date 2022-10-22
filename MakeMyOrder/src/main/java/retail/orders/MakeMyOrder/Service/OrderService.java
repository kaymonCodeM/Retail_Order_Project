package retail.orders.MakeMyOrder.Service;

import retail.orders.MakeMyOrder.Componets.OrderRequest;
import retail.orders.MakeMyOrder.Componets.UpdateOrderRequest;
import retail.orders.MakeMyOrder.Entity.*;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    List<Order> findOrders();

    Order addOrder(OrderRequest orderRequest);
    String cancelOrderById(long order);

    Order findOrderById(long orderId);

    String deleteOrderById(long orderId);

    String setShippedById(long orderId);

    String setDeliveredById(long orderId, LocalDate deliveredDate);

    String updateOrder(UpdateOrderRequest updateOrderRequest);
}
