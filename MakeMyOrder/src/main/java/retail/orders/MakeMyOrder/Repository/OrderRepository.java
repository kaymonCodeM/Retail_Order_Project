package retail.orders.MakeMyOrder.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import retail.orders.MakeMyOrder.Entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
