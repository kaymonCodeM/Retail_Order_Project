package retail.orders.MakeMyOrder.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import retail.orders.MakeMyOrder.Entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
}
