package retail.orders.MakeMyOrder.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import retail.orders.MakeMyOrder.Entity.Payment;
import retail.orders.MakeMyOrder.Entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    @Query(value = "SELECT * FROM tbl_payment WHERE user_id = ?1",nativeQuery = true)
    List<Payment> findPaymentsByUserId(long userId);
}
