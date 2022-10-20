package retail.orders.MakeMyOrder.Service;

import retail.orders.MakeMyOrder.Entity.Item;

import java.util.List;

public interface ItemService {

    List<Item> getItems();
    Item getItemById(long itemId);
    Item addItem(String name,String type, String imageUrl, String description, int quantity, double price,String size,double weight);
    Item updateItem(Item item);
    String removeItemById(long itemId);

}
