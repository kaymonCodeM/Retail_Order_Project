package retail.orders.MakeMyOrder.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retail.orders.MakeMyOrder.Entity.*;
import retail.orders.MakeMyOrder.Repository.*;

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
    private ContactRepository contactRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PaymentRepository paymentRepository;

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

    private String uploadOrderFile(Order order){

        String filePath = FILE_ORDER_URL +order.getUser().getUsername()+"/";
        double totalCost = 0.0;

        String content = "ORDER: " + order.getOrderId() + "\n";
        content += "from: " + order.getUser().getUsername() +"\n\n";

        content+= "User Information:\n";
        content += "Contact:\n"
                + "firstname: " + order.getContact().getFirstname() + "\n"
                + "lastname: " + order.getContact().getLastname() + "\n"
                + "email: " +order.getContact().getEmail() +"\n"
                + "phone number: " + order.getContact().getPhoneNumber() + "\n\n";
        content += "Address:\n"
                + "street address: " + order.getAddress().getStreetAddress() + "\n"
                + "country: " + order.getAddress().getCountry() + "\n"
                + "city: " + order.getAddress().getCity() + "\n"
                + "state: " + order.getAddress().getState() + "\n"
                + "zip: " + order.getAddress().getZip() + "\n\n";
        content += "Payment:\n"
                + "cardholder: " + order.getPayment().getCardHolder() + "\n"
                + "card number: " + order.getPayment().getCardNumber() + "\n"
                + "expiration date: " +order.getPayment().getExpirationDate() +"\n"
                + "cvv: " + order.getPayment().getCvv() + "\n"
                + "zip: " + order.getPayment().getZip() + "\n\n";


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

    private User validateUser(long userId){
        Optional<User> user = userRepository.findById(userId);
        return user.orElse(null);
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
    public List<Order> findOrdersByUserId(long userId) {
        return orderRepository.findOrderByUserId(userId);
    }

    @Override
    public Order saveOrder(Order order) {

        order.setOrderDate(LocalDate.now());

        Payment savePayment = paymentRepository.save(order.getPayment());
        Address saveAddress = addressRepository.save(order.getAddress());
        Contact saveContact = contactRepository.save(order.getContact());

        order.setPayment(savePayment);
        order.setAddress(saveAddress);
        order.setContact(saveContact);


        Order saveOrder = orderRepository.save(order);

        String orderUrl = uploadOrderFile(saveOrder);
        saveOrder.setOrderSummeryUrl(orderUrl);

        Order reSavedOrder = orderRepository.save(saveOrder);

        for (Transaction transaction: order.getTransactions()){

            Item item = transaction.getItem();
            item.setQuantity(item.getQuantity()-transaction.getQuantity());
            Item saveItem = itemRepository.save(item);

            transaction.setItem(saveItem);
            transaction.setOrder(reSavedOrder);
            transactionRepository.save(transaction);
        }


        //orderRequest.setSubject("ORDER Request from: " + order.getUser().getUsername());
        //System.out.println(emailOrderDetails(orderRequest.getSubject(),orderUrl));

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
        return order.orElse(null);
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
    public Order updateOrder(Order order) {

        if(!order.isShipped()) {
            for (Transaction transaction: transactionRepository.findTransactionsByOrderId(order.getOrderId())){
                Item item = transaction.getItem();
                item.setQuantity(item.getQuantity()+transaction.getQuantity());
                Item saveItem = itemRepository.save(item);

                transaction.setItem(saveItem);
                transactionRepository.delete(transaction);
            }
        }else {
            return null;
        }

        return saveOrder(order);
    }

}
