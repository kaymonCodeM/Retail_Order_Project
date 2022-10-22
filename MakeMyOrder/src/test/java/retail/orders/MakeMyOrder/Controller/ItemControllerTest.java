package retail.orders.MakeMyOrder.Controller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import retail.orders.MakeMyOrder.Entity.Item;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ItemControllerTest {

    @Autowired
    private ItemController itemController;

    @Test
    void addItem(){
        Item item = new Item("Can","product","/product","What a can",5,2.25,"1x2",2.1);
        Item test = itemController.addItem(item);
        assertNotNull(test, "Add Item Failed");
    }
}