package vincentlow.parkee.parkingpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vincentlow.parkee.parkingpos.model.entity.CheckInTicket;

@Repository
public interface CheckInTicketRepository extends JpaRepository<CheckInTicket, String> {

  boolean existsByPlateNumberAndParkingSpotParkingLotIdAndIsActiveTrue(String plateNumber, String parkingLotId);

  CheckInTicket findByIdAndPlateNumberAndParkingSpotParkingLotIdAndIsActiveTrue(String id, String plateNumber,
      String parkingLotId);
}
