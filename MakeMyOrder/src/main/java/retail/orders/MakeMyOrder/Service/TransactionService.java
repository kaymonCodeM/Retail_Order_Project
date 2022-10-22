package retail.orders.MakeMyOrder.Service;

import retail.orders.MakeMyOrder.Entity.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> findAllTransactions();
    Transaction addTransaction(long itemId,int quantity);
    Transaction updateTransaction(Transaction transaction);
    Transaction getTransactionById(long transactionId);
    String deleteTransaction(long transactionId);
}
