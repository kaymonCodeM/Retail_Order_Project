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
        Item item1 = new Item("Can","product","/product","What a can",5,2.25,"1x2",2.1);
        Item item2 = new Item("Block","chain","/chain","This blokc it nice to sit on",8,5.25,"12x8",20);
        Item test1 = itemController.addItem(item1);
        Item test2 = itemController.addItem(item2);
        assertNotNull(test1, "Add Item Failed");
        assertNotNull(test2, "Add Item Failed");
    }
}