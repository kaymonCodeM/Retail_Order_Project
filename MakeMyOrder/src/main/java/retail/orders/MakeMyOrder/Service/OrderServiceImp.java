package retail.orders.MakeMyOrder.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retail.orders.MakeMyOrder.Entity.*;
import retail.orders.MakeMyOrder.MakeMyOrderApplication;
import retail.orders.MakeMyOrder.Repository.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;

@Service
public class OrderServiceImp implements OrderService{


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PaymentRepository paymentRepository;


    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ItemRepository itemRepository;

    private Logger log = LoggerFactory.getLogger(MakeMyOrderApplication.class);

    //https://www.baeldung.com/java-add-text-to-image
    private double calculateDeliveryCharge(Item item,int quantity){
        return item.getWeight() *.15 * quantity;
    }

    private String uploadOrderFile(Order order){

        String filePath = "src/main/resources/RetailOrders/" +order.getUser().getUsername()+"/";
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
            log.error("Error: File was not found while creating order number " + order.getOrderId());
            return "File was not created";
        }
        log.debug("Order file number " + order.getOrderId() + " is now created file path: " + filePath);
        return filePath;
    }



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
        List<Transaction> transactions = new ArrayList<>(order.getTransactions());

        Payment savePayment = paymentRepository.save(order.getPayment());
        Address saveAddress = addressRepository.save(order.getAddress());
        Contact saveContact = contactRepository.save(order.getContact());

        log.debug("Payment number " + savePayment.getPaymentId() + " is now saved");
        log.debug("Address number " + saveAddress.getAddressId() + " is now saved");
        log.debug("Contact number " + saveContact.getContactId() + " is now saved");

        order.setPayment(savePayment);
        order.setAddress(saveAddress);
        order.setContact(saveContact);

        Order saveOrder = orderRepository.save(order);
        order.setOrderSummeryUrl(uploadOrderFile(saveOrder));
        Order reSavedOrder = orderRepository.save(saveOrder);

        log.debug("Order number " + reSavedOrder.getOrderId() + " is now saved");


        for (Transaction transaction: transactions){

            Item item = transaction.getItem();
            item.setQuantity(item.getQuantity()-transaction.getQuantity());
            Item saveItem = itemRepository.save(item);
            log.debug("Item number " + item.getItemId() + " quantity is now updated");

            transaction.setItem(saveItem);
            transaction.setOrder(reSavedOrder);
            transactionRepository.save(transaction);
            log.debug("Transaction number " + transaction.getTransactionId()+ " is now saved");
        }

        log.debug("order number: "+ reSavedOrder.getOrderId()+ " is now saved");
        return reSavedOrder;
    }

    @Override
    public String cancelOrderById(long orderId) {
        Order order = findOrderById(orderId);
        //System.out.println(emailOrderDetails("Cancel Order Request from: " + order.getUser().getUsername(),order.getOrderSummeryUrl()));
        if(order.isShipped()){
            log.debug("Order number "+orderId + " is shipped but tried to cancel order");
            return "Order already shipped";
        }
        if(order.isComplete()){
            log.debug("Order number "+orderId + " is complete but tried to cancel order");
            return "Order already complete";
        }
        order.setCanceled(true);
        log.debug("Order number " + orderId + "is now canceled");
        return "Cancel Successful";
    }

    @Override
    public Order findOrderById(long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        return order.orElse(null);
    }

    @Override
    public String deleteOrderById(long orderId) {
        Order order = findOrderById(orderId);

        if(order==null){
            log.error("Tried to delete but orderId: " + orderId + " was not found");
            return "Order was not found";
        }

        Address address = order.getAddress();
        Contact contact = order.getContact();
        Payment payment = order.getPayment();

        for (Transaction transaction: transactionRepository.findTransactionsByOrderId(orderId)){
            transactionRepository.delete(transaction);
            log.debug("Transaction number " + transaction.getTransactionId() + " is now deleted");
        }

        orderRepository.delete(order);
        addressRepository.delete(address);
        contactRepository.delete(contact);
        paymentRepository.delete(payment);

        log.debug("Address number " + address.getAddressId() + " is now deleted");
        log.debug("Contact number " + contact.getContactId() + " is now deleted");
        log.debug("Payment number " + payment.getPaymentId() + " is now deleted");

        log.debug("Order number " + orderId + " is now deleted");
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
    public String setDeliveredById(long orderId) {
        Order order = findOrderById(orderId);
        order.setDeliveryDate(LocalDate.now());
        order.setComplete(true);
        orderRepository.save(order);
        log.debug("Order number " + orderId + " is now shipped");
        return "Order is now complete";
    }

    @Override
    public Order updateOrder(Order order) {

        if(!findOrderById(order.getOrderId()).isShipped()) {

            for (Transaction transaction: transactionRepository.findTransactionsByOrderId(order.getOrderId())){
                Item item = transaction.getItem();
                item.setQuantity(item.getQuantity()+transaction.getQuantity());
                Item saveItem = itemRepository.save(item);
                log.debug("Item number " + item.getItemId() + " quantity is now updated");

                transaction.setItem(saveItem);
                transactionRepository.delete(transaction);
                log.debug("Transaction number " + transaction.getTransactionId() + " is now deleted");
            }


        }else {
            log.error("Order number " + order.getOrderId() + " was not found");
            return null;
        }

        log.debug("Order number " + order.getOrderId() + " is now updated");

        return saveOrder(order);
    }

    @Override
    public Address findAddressByOrderId(long orderId) {
        return findOrderById(orderId).getAddress();
    }

    @Override
    public Contact findContactByOrderId(long orderId) {
        return findOrderById(orderId).getContact();
    }

    @Override
    public Payment findPaymentByOrderId(long orderId) {
        return findOrderById(orderId).getPayment();
    }

    @Override
    public User findUserByOrderId(long orderId) {
        return findOrderById(orderId).getUser();
    }

    @Override
    public List<Transaction> findTransactionsByOrderId(long orderId) {
        return transactionRepository.findTransactionsByOrderId(orderId);
    }

    @Override
    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }


}
