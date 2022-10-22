package retail.orders.MakeMyOrder.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retail.orders.MakeMyOrder.Entity.Item;
import retail.orders.MakeMyOrder.Entity.Transaction;
import retail.orders.MakeMyOrder.Repository.ItemRepository;
import retail.orders.MakeMyOrder.Repository.TransactionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImp implements ItemService{

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private TransactionRepository transactionRepository;


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
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item updateItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public String removeItemById(long itemId) {
        Item item = getItemById(itemId);
        for(Transaction transaction: item.getTransactions()){
            transactionRepository.delete(transaction);
        }
        itemRepository.delete(item);
        return "Deleted item by id: " + itemId;
    }
}
