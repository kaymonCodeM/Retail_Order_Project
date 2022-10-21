package retail.orders.MakeMyOrder.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retail.orders.MakeMyOrder.Entity.*;
import retail.orders.MakeMyOrder.Repository.OrderRepository;
import retail.orders.MakeMyOrder.Repository.UserRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.*;

@Service
public class OrderServiceImp implements OrderService{

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    private final String FILE_ORDER_URL = "MakeMyOrder/src/main/resources/RetailOrders/";



    private double calculateDeliveryCharge(Set<Item> items){
        double result = 0.0;
        for (Item i:items){
            result += i.getWeight() *.05;
        }
        return result;
    }

    private String uploadOrderFile(User user, Map<Item,Integer> items, Contact contact, Address address, Payment payment,long orderId){

        String filePath = FILE_ORDER_URL+user.getUsername()+"/"+orderId;

        String content = "ORDER: " + orderId + "\n";
        content += "from: " + user.getUsername() +"\n\n";

        for (Item item: items.keySet()){
            content += item +"\n" + "quantity: "+ items.get(item);
        }

        content += contact+"\n\n";
        content += address +"\n\n";
        content += payment;


        try {
            Files.write(
                    Paths.get(filePath),
                    content.getBytes(),
                    StandardOpenOption.APPEND);
        }catch (Exception e){
            return "File was not created successfully";
        }
        return filePath;
    }

    private String emailOrderDetails(String subject,String orderUrl){
        try {
            String body = Files.readString(Path.of(orderUrl));
            System.out.println(emailSenderService.sendEmail(subject,body));
        }catch (Exception e){
            return "ERROR email order was not successful";
        }
        return "Email order details successfully";
    }


    @Override
    public List<Order> findOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order addOrder(long userId, Map<Item,Integer> items, Contact contact, Address address, Payment payment) {
        User user;
        Optional<User> u = userRepository.findById(userId);
        if(u.isPresent()){
            user = u.get();
        }else {
            throw new RuntimeException("User was not found by Id: " + userId);
        }

        Order newOrder = new Order(LocalDate.now(),null,calculateDeliveryCharge(items.keySet()),false,false,false,"",new ArrayList<>(items.keySet()),user);


        String orderUrl = uploadOrderFile(user,items,contact,address,payment,newOrder.getOrderId());
        newOrder.setOrderSummeryUrl(orderUrl);

        newOrder.setUser(user);
        user.getOrders().add(newOrder);

        userRepository.save(user);

        String subject = "ORDER from: " + user.getUsername();
        System.out.println(emailOrderDetails(subject,orderUrl));

        return orderRepository.save(newOrder);
    }

    @Override
    public String cancelOrderById(long orderId) {
        Order order = findOrderById(orderId);
        System.out.println(emailOrderDetails("Cancel Order Request from: " + order.getUser().getUsername(),order.getOrderSummeryUrl()));
        if(order.isShipped()){
            return "Order already shipped";
        }
        if(order.isComplete()){
            return "Order already complete";
        }
        order.setCanceled(true);
        return "Cancel Successful";
    }

    @Override
    public Order findOrderById(long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()){
            return order.get();
        }else {
            throw new RuntimeException("Order ID was not found: " + orderId);
        }
    }

    @Override
    public String deleteOrderById(long orderId) {
        Order order = findOrderById(orderId);
        try {
            Files.delete(Path.of(order.getOrderSummeryUrl()));
        }catch (Exception e){
            System.out.println("File was not found: " +order.getOrderSummeryUrl());
        }
        order.getUser().getOrders().remove(order);
        userRepository.save(order.getUser());
        orderRepository.delete(order);
        return "Delete Order Successful";
    }

    @Override
    public String setShippedById(long orderId) {
        Order order = findOrderById(orderId);
        order.setShipped(true);
        orderRepository.save(order);
        return "Order is now shipping";
    }

    @Override
    public String setDeliveredById(long orderId, LocalDate deliveredDate) {
        Order order = findOrderById(orderId);
        order.setDeliveryDate(deliveredDate);
        order.setComplete(true);
        orderRepository.save(order);
        return "Order is now complete";
    }
}
