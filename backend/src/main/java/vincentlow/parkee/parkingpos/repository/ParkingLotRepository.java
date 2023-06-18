package vincentlow.parkee.parkingpos.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vincentlow.parkee.parkingpos.model.entity.ParkingLot;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, String> {

  @EntityGraph(attributePaths = "vehicleTypes")
  ParkingLot findByIdAndMarkForDeleteFalse(String id);
}
