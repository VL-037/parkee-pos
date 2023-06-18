package vincentlow.parkee.parkingpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vincentlow.parkee.parkingpos.model.entity.ParkingRate;

@Repository
public interface ParkingRateRepository extends JpaRepository<ParkingRate, String> {

  ParkingRate findTopByMarkForDeleteFalse();
}
