package retail.orders.MakeMyOrder.Service;

import org.slf4j.Logger;
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

    @Autowired
    private Logger log;

    @Override
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item getItemById(long itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        return item.orElse(null);
    }

    @Override
    public Item addItem(Item item) {
        log.debug("Item saved successfully");
        return itemRepository.save(item);
    }

    @Override
    public Item updateItem(Item item) {
        log.debug("Item id: "+ item.getItemId() + " is now updated");
        return itemRepository.save(item);
    }

    @Override
    public String removeItemById(long itemId) {
        for(Transaction transaction: transactionRepository.findTransactionsByItemId(itemId)){
            log.debug("Transaction id: "+ transaction.getTransactionId() + " is now deleted");
            transactionRepository.delete(transaction);
        }
        itemRepository.deleteById(itemId);
        log.debug("Item id: "+ itemId + " is now deleted");
        return "Deleted item successfully by id: " + itemId;
    }
}
