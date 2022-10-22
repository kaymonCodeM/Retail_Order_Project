package retail.orders.MakeMyOrder.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import retail.orders.MakeMyOrder.Entity.Transaction;

import java.util.List;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    @Query(value = "SELECT * FROM tbl_transaction WHERE order_id = ?1",nativeQuery = true)
    List<Transaction> findTransactionsByOrderId(long orderId);

    @Query(value = "SELECT * FROM tbl_transaction WHERE item_id = ?1",nativeQuery = true)
    List<Transaction> findTransactionsByItemId(long itemId);
}
