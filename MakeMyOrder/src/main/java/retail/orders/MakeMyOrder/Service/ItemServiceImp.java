package retail.orders.MakeMyOrder.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retail.orders.MakeMyOrder.Entity.Item;
import retail.orders.MakeMyOrder.Repository.ItemRepository;
import retail.orders.MakeMyOrder.Repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImp implements ItemService{

    @Autowired
    private ItemRepository itemRepository;


    @Override
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item getItemById(long itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        if(item.isPresent()){
            return item.get();
        }else {
            throw new RuntimeException("Item was not found by id: " + itemId);
        }
    }

    @Override
    public Item addItem(String name,String type, String imageUrl, String description, int quantity, double price,String size,double weight) {
        Item item = new Item(name,type,imageUrl,description,quantity,price,size,weight,new ArrayList<>());
        return itemRepository.save(item);
    }

    @Override
    public Item updateItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public String removeItemById(long itemId) {
        itemRepository.deleteById(itemId);
        return "Deleted item by id: " + itemId;
    }
}
