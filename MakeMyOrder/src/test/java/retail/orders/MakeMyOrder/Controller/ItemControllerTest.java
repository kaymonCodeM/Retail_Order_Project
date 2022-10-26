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
        Item item1 = new Item("Can","Product","/product","What a can",5,2.25,"1x2",2.1);
        Item item2 = new Item("Block","Chain","/chain","This blokc it nice to sit on",8,5.25,"12x8",20);
        Item test1 = itemController.addItem(item1);
        Item test2 = itemController.addItem(item2);
        assertNotNull(test1, "Add Item Failed");
        assertNotNull(test2, "Add Item Failed");
    }
    @Test
    void updateItem(){
        Item item1 = new Item("Can","Product","can.jpg","What a can",5,2.25,"1x2",2.1);
        Item item2 = new Item("Block","Home-Goods","block.jpg","This block it nice to sit on",8,5.25,"12x8",20);
        item1.setItemId(1);
        item2.setItemId(2);
        Item test1 = itemController.updateItem(item1);
        Item test2 = itemController.updateItem(item2);
        assertNotNull(test1, "Add Item Failed");
        assertNotNull(test2, "Add Item Failed");
    }
}