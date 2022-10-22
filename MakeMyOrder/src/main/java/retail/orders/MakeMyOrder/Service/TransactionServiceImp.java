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
public class TransactionServiceImp implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ItemRepository itemRepository;


    @Override
    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction addTransaction(long itemId, int quantity) {
        Optional<Item> i = itemRepository.findById(itemId);
        Item item;
        if(i.isPresent()){
            item = i.get();
        }else {
            throw new RuntimeException("Item was not found by id: " + itemId);
        }
        Transaction transaction = new Transaction(item,quantity);
        item.getTransactions().add(transaction);
        itemRepository.save(item);
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getTransactionById(long transactionId) {
        Optional<Transaction> t = transactionRepository.findById(transactionId);
        if(t.isPresent()){
            return t.get();
        }else {
            throw new RuntimeException("Transaction was not found by id: " + transactionId);
        }
    }

    @Override
    public String deleteTransaction(long transactionId) {
        transactionRepository.deleteById(transactionId);
        return "Deleted Successfully";
    }
}
