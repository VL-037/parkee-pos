package vincentlow.parkee.parkingpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vincentlow.parkee.parkingpos.model.entity.CheckOutTicket;

@Repository
public interface CheckOutTicketRepository extends JpaRepository<CheckOutTicket, String> {
}
