package retail.orders.MakeMyOrder.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import retail.orders.MakeMyOrder.Componets.OrderRequest;
import retail.orders.MakeMyOrder.Entity.*;
import retail.orders.MakeMyOrder.Service.ItemService;
import retail.orders.MakeMyOrder.Service.TransactionService;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class OrderControllerTest {

    @Autowired
    private OrderController orderController;

    @Autowired
    private ItemService itemService;

    @Autowired
    private TransactionService transactionService;

    @Test
    void addOrder(){
        Set<Transaction> transactions = new HashSet<>();
        transactions.add(transactionService.addTransaction(1,1));
        transactions.add(transactionService.addTransaction(2,2));
        Address address = new Address("741 M ST","NE","Lincoln","NE","54641");
        Contact contact = new Contact("Kaymon","McCain","jhskfjh@snfk.con","251512124");
        Payment payment = new Payment("Kaymon","655118151514",LocalDate.of(2023,05,24) ,541,"6261511");

        OrderRequest orderRequest = new OrderRequest(1,transactions,address,contact,payment);
        Order order = orderController.addOrder(orderRequest);
        assertNotNull(order, "Add Order Failed");
    }
}