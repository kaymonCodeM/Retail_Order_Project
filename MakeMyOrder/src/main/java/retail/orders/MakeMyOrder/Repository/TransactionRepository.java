package retail.orders.MakeMyOrder.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import retail.orders.MakeMyOrder.Entity.Transaction;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
