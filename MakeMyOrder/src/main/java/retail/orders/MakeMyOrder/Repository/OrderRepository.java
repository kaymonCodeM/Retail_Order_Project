package retail.orders.MakeMyOrder.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import retail.orders.MakeMyOrder.Entity.Order;
import retail.orders.MakeMyOrder.Entity.Payment;
import retail.orders.MakeMyOrder.Entity.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query(value = "SELECT * FROM tbl_order WHERE user_id = ?1",nativeQuery = true)
    List<Order> findOrderByUserId(long userId);
}
