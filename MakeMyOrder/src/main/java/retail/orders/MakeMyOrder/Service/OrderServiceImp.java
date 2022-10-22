package retail.orders.MakeMyOrder.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retail.orders.MakeMyOrder.Componets.OrderRequest;
import retail.orders.MakeMyOrder.Componets.UpdateOrderRequest;
import retail.orders.MakeMyOrder.Entity.*;
import retail.orders.MakeMyOrder.Repository.ItemRepository;
import retail.orders.MakeMyOrder.Repository.OrderRepository;
import retail.orders.MakeMyOrder.Repository.TransactionRepository;
import retail.orders.MakeMyOrder.Repository.UserRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;

@Service
public class OrderServiceImp implements OrderService{

    //@Autowired
    //private EmailSenderService emailSenderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ItemRepository itemRepository;

    //https://www.baeldung.com/java-add-text-to-image
    private final String FILE_ORDER_URL = "src/main/resources/RetailOrders/";



    private double calculateDeliveryCharge(Item item,int quantity){
        return item.getWeight() *.15 * quantity;
    }

    private String uploadOrderFile(Contact contact, Address address, Payment payment,Order order){

        String filePath = FILE_ORDER_URL +order.getUser().getUsername()+"/";
        double totalCost = 0.0;

        String content = "ORDER: " + order.getOrderId() + "\n";
        content += "from: " + order.getUser().getUsername() +"\n\n";

        content+= "User Information:\n";
        content += "Contact:\n"
                + "firstname: " + contact.getFirstname() + "\n"
                + "lastname: " + contact.getLastname() + "\n"
                + "email: " +contact.getEmail() +"\n"
                + "phone number: " + contact.getPhoneNumber() + "\n\n";
        content += "Address:\n"
                + "street address: " + address.getStreetAddress() + "\n"
                + "country: " + address.getCountry() + "\n"
                + "city: " + address.getCity() + "\n"
                + "state: " + address.getState() + "\n"
                + "zip: " + address.getZip() + "\n\n";
        content += "Payment:\n"
                + "cardholder: " + payment.getCardHolder() + "\n"
                + "card number: " + payment.getCardNumber() + "\n"
                + "expiration date: " +payment.getExpirationDate() +"\n"
                + "cvv: " + payment.getCvv() + "\n"
                + "zip: " + payment.getZip() + "\n\n";


        content += "List of Items:\n";
        double deliveryCost = 0.0;
        for (Transaction transaction: order.getTransactions()){

            Item item = transaction.getItem();
            double itemCost = item.getPrice()*transaction.getQuantity();
            totalCost += itemCost;
            content +="Item: "+ item.getName() +"\n"
                    + "Item ID: " + item.getItemId() + "\n"
                    + "type: " + item.getType() + "\n"
                    + "size: " + item.getSize() + "\n"
                    + "weight: " + item.getWeight() + "\n"
                    + "quantity: " + transaction.getQuantity() + "\n"
                    + "cost: " + String.format("$ %.2f",itemCost)
                    +"\n\n";

            deliveryCost = calculateDeliveryCharge(item,transaction.getQuantity());
        }

        totalCost += deliveryCost;
        content += "delivery charge: " + String.format("$ %.2f",deliveryCost) +"\n\n";
        content += "total: " + String.format("$ %.2f",totalCost);

        order.setTotalCost(totalCost);


        try {
            Files.createDirectories(Path.of(filePath));
            filePath +="order"+ order.getOrderId()+ ".txt";
            Files.writeString(Path.of(filePath),content);
        }catch (Exception e){
            return "File was not created successfully";
        }
        return filePath;
    }

//    private String emailOrderDetails(String subject,String orderUrl){
//        try {
//            String body = Files.readString(Path.of(orderUrl));
//            System.out.println(emailSenderService.sendEmail(subject,body));
//        }catch (Exception e){
//            return "ERROR email order was not successful";
//        }
//        return "Email order details successfully";
//    }


    @Override
    public List<Order> findOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order addOrder(OrderRequest orderRequest) {
        User user;
        Optional<User> u = userRepository.findById(orderRequest.getUserId());
        if(u.isPresent()){
            user = u.get();
        }else {
            throw new RuntimeException("User was not found by Id: " + orderRequest.getUserId());
        }

        Order newOrder = new Order(LocalDate.now(),orderRequest.getTransactions(),user);

        Order saveOrder = orderRepository.save(newOrder);
        String orderUrl = uploadOrderFile(orderRequest.getContact(),orderRequest.getAddress(),orderRequest.getPayment(),saveOrder);
        saveOrder.setOrderSummeryUrl(orderUrl);

        Order reSavedOrder = orderRepository.save(saveOrder);

        for (Transaction transaction: orderRequest.getTransactions()){

            Item item = transaction.getItem();
            item.setQuantity(item.getQuantity()-transaction.getQuantity());
            Item saveItem = itemRepository.save(item);

            transaction.setItem(saveItem);
            transaction.setOrder(reSavedOrder);
            transactionRepository.save(transaction);
        }


        //String subject = "ORDER from: " + user.getUsername();
        //System.out.println(emailOrderDetails(subject,orderUrl));

        return reSavedOrder;
    }

    @Override
    public String cancelOrderById(long orderId) {
        Order order = findOrderById(orderId);
        //System.out.println(emailOrderDetails("Cancel Order Request from: " + order.getUser().getUsername(),order.getOrderSummeryUrl()));
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
        for (Transaction transaction: transactionRepository.findTransactionsByOrderId(orderId)){
            transactionRepository.delete(transaction);
        }
        orderRepository.deleteById(orderId);
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

    @Override
    public String updateOrder(UpdateOrderRequest updateOrderRequest) {

        Order order = findOrderById(updateOrderRequest.getOrderId());

        if(!order.isShipped()) {
            for (Transaction transaction: transactionRepository.findTransactionsByOrderId(updateOrderRequest.getOrderId())){
                Item item = transaction.getItem();
                item.setQuantity(item.getQuantity()+transaction.getQuantity());
                Item saveItem = itemRepository.save(item);

                transaction.setItem(saveItem);
                transactionRepository.delete(transaction);
            }

            order.setTransactions(updateOrderRequest.getTransactions());
            String orderUrl = uploadOrderFile(updateOrderRequest.getContact(), updateOrderRequest.getAddress(), updateOrderRequest.getPayment(), order);
            order.setOrderSummeryUrl(orderUrl);

            Order saveOrder = orderRepository.save(order);

            for (Transaction transaction: updateOrderRequest.getTransactions()){
                transaction.setOrder(saveOrder);
                transactionRepository.save(transaction);
            }

//            String subject = "UPDATE ORDER from: " + order.getUser();
//            System.out.println(emailOrderDetails(subject, orderUrl));
        }else {
            return "Error: Order has already been shipped";
        }

        return "Successfully updated order";
    }

}
