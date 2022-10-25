package retail.orders.MakeMyOrder.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import retail.orders.MakeMyOrder.Entity.*;
import retail.orders.MakeMyOrder.Service.ItemService;
import retail.orders.MakeMyOrder.Service.MyUserDetailsService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class OrderControllerTest {

    @Autowired
    private OrderController orderController;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private ItemService itemService;


    @Test
    void addOrder(){
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction1 = new Transaction(itemService.getItemById(1),1);
        Transaction transaction2 = new Transaction(itemService.getItemById(2),2);

        transactions.add(transaction1);
        transactions.add(transaction2);

        Address address = new Address("741 M ST","NE","Lincoln","NE","54641");
        Contact contact = new Contact("Kaymon","McCain","mkaymon@gmail.com","251512124");
        Payment payment = new Payment("Kaymon","655118151514",LocalDate.of(2023,05,24) ,541,"6261511");

        User user = myUserDetailsService.getUserById(9);
        Order order = new Order(payment,contact,address,transactions,user);

        Order result = orderController.addOrder(order);

        assertNotNull(result, "Add Order Failed");
    }

    @Test
    void getAllTransactions() throws JSONException {
        List<Transaction> transactions = orderController.findAllTransactions();
        assertNotNull(transactions, "Add Order Failed");
    }

    @Test
    void getAllOrders(){
        List<Order> orders = orderController.findAllOrders();
        assertNotNull(orders, "Add Order Failed");
    }
}