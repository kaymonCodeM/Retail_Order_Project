package retail.orders.MakeMyOrder.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import retail.orders.MakeMyOrder.Componets.OrderRequest;
import retail.orders.MakeMyOrder.Entity.*;
import retail.orders.MakeMyOrder.Service.OrderService;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order/order+1")
    Order addOrder(@RequestBody OrderRequest orderRequest){
        return orderService.addOrder(orderRequest);
    }
}
