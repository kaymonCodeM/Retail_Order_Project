package retail.orders.MakeMyOrder.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import retail.orders.MakeMyOrder.Entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
