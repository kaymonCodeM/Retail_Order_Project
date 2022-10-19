package retail.orders.MakeMyOrder.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import retail.orders.MakeMyOrder.Entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
}
