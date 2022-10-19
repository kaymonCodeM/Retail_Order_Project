package retail.orders.MakeMyOrder.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import retail.orders.MakeMyOrder.Entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {
}
