package retail.orders.MakeMyOrder.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import retail.orders.MakeMyOrder.Entity.*;
import retail.orders.MakeMyOrder.MakeMyOrderApplication;
import retail.orders.MakeMyOrder.Service.EmailSenderService;
import retail.orders.MakeMyOrder.Service.OrderService;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private EmailSenderService emailSenderService;


    private Logger log = LoggerFactory.getLogger(MakeMyOrderApplication.class);

    @Autowired
    private String myEmail;

    @GetMapping("/order/all")
    List<Order> findAllOrders(){
        return orderService.findOrders();
    }

    @GetMapping("/order/byUser/{userId}")
    List<Order> findAllUserOrdersById(@PathVariable String userId){
        return orderService.findOrdersByUserId(Long.parseLong(userId));
    }

    @PutMapping("/order/cancel/{orderId}")
    String cancelOrder(@PathVariable String orderId){
        return orderService.cancelOrderById(Long.parseLong(orderId));
    }

    @PutMapping("/order/shipped/{orderId}")
    String shipOrder(@PathVariable String orderId){
        return orderService.setShippedById(Long.parseLong(orderId));
    }

    @PutMapping("/order/delivered/{orderId}")
    String deliverOrder(@PathVariable String orderId){
        return orderService.setDeliveredById(Long.parseLong(orderId));
    }

    @PutMapping("/order/update")
    Order updateOrder(@RequestBody Order order){
        return orderService.updateOrder(order);
    }

    @GetMapping("/order/{orderId}")
    Order getOrderById(@PathVariable String orderId){
        return orderService.findOrderById(Long.parseLong(orderId));
    }

    @DeleteMapping("/order/{orderId}")
    String deleteOrderById(@PathVariable String orderId){
        return orderService.deleteOrderById(Long.parseLong(orderId));
    }

    @PostMapping("/order/add")
    String addOrder(@RequestBody Order order){
        Order o =  orderService.saveOrder(order);
        if(o!=null) {
            String subject = "ORDER Request from: " + order.getUser().getUsername();
            log.info(emailSenderService.emailOrderDetails(o.getContact().getEmail(),subject,o.getOrderSummeryUrl()));
            log.info(emailSenderService.emailOrderDetails(myEmail,subject,o.getOrderSummeryUrl()));
            return "Order Successful";
        }
        return "Order not Successful";
    }

    @GetMapping("/order/address/{orderId}")
    Address getAddressByOrderId(@PathVariable String orderId){
        return orderService.findAddressByOrderId(Long.parseLong(orderId));
    }

    @GetMapping("/order/contact/{orderId}")
    Contact getContactByOrderId(@PathVariable String orderId){
        return orderService.findContactByOrderId(Long.parseLong(orderId));
    }

    @GetMapping("/order/payment/{orderId}")
    Payment getPaymentByOrderId(@PathVariable String orderId){
        return orderService.findPaymentByOrderId(Long.parseLong(orderId));
    }

    @GetMapping("/order/user/{orderId}")
    User getUserByOrderId(@PathVariable String orderId){
        return orderService.findUserByOrderId(Long.parseLong(orderId));
    }

    @GetMapping("/order/transactions/{orderId}")
    List<Transaction> getTransactionsById(@PathVariable String orderId){
        return orderService.findTransactionsByOrderId(Long.parseLong(orderId));
    }
    @GetMapping("/transactions")
    List<Transaction> findAllTransactions(){
        return orderService.findAllTransactions();
    }

    @PostMapping("/order/giftMessage")
    String sendGiftMessage(Contact contact,String message){
        String subject = "Gift message from: " + contact.getFirstname();
        return emailSenderService.sendEmail(contact.getEmail(),subject,message);
    }
}
