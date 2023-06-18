package vincentlow.parkee.parkingpos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vincentlow.parkee.parkingpos.model.entity.ParkingSpot;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, String> {

  Page<ParkingSpot> findAllByParkingLotIdAndIsOccupiedFalseAndMarkForDeleteFalseOrderByAreaAscCodeAsc(
      String parkingLotId, Pageable pageable);

  int countByParkingLotIdAndIsOccupiedTrue(String parkingLotId);

  ParkingSpot findTopByParkingLotIdAndVehicleTypeIdAndIsOccupiedFalseAndMarkForDeleteFalse(
      String parkingLotId, String vehicleTypeId);

  ParkingSpot findByIdAndMarkForDeleteFalse(String id);
}
