package retail.orders.MakeMyOrder.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retail.orders.MakeMyOrder.Entity.*;
import retail.orders.MakeMyOrder.Repository.OrderRepository;
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

    private final String FILE_ORDER_URL = "MakeMyOrder/src/main/resources/RetailOrders/";



    private double calculateDeliveryCharge(Set<Item> items){
        double result = 0.0;
        for (Item i:items){
            result += i.getWeight() *.05;
        }
        return result;
    }

    private String uploadOrderFile(User user, Map<Item,Integer> items, Contact contact, Address address, Payment payment,Order order){

        String filePath = FILE_ORDER_URL+user.getUsername()+"/"+order.getOrderId();

        String content = "ORDER: " + order.getOrderId() + "\n";
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
    public Order addOrder(User user, Map<Item,Integer> items, Contact contact, Address address, Payment payment) {

        Order newOrder = new Order(LocalDate.now(),null,calculateDeliveryCharge(items.keySet()),false,false,false,"",new ArrayList<>(items.keySet()),user);
        String orderUrl = uploadOrderFile(user,items,contact,address,payment,newOrder);

        newOrder.setOrderSummeryUrl(orderUrl);
        newOrder.setUser(user);

        user.getOrders().add(newOrder);
        String subject = "ORDER from: " + user.getUsername();

        System.out.println(emailOrderDetails(subject,orderUrl));
        return orderRepository.save(newOrder);
    }

    @Override
    public String cancelOrderById(Order order) {
        System.out.println(emailOrderDetails("Cancel Order Request from: " + order.getUser().getUsername(),order.getOrderSummeryUrl()));
        if(order.isShipped()){
            return "Order already shipped";
        }
        if(order.isComplete()){
            return "Order already complete";
        }
        return deleteOrder(order);
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
    public String deleteOrder(Order order) {
        try {
            Files.delete(Path.of(order.getOrderSummeryUrl()));
        }catch (Exception e){
            System.out.println("File was not found: " +order.getOrderSummeryUrl());
        }
        order.getUser().getOrders().remove(order);
        orderRepository.delete(order);
        return "Delete Order Successful";
    }
}
