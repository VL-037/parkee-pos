package vincentlow.parkee.parkingpos.service;

import org.springframework.data.domain.Page;

import vincentlow.parkee.parkingpos.model.entity.ParkingLot;
import vincentlow.parkee.parkingpos.model.entity.ParkingSpot;
import vincentlow.parkee.parkingpos.model.request.GetParkingLotDetailRequest;
import vincentlow.parkee.parkingpos.model.request.GetParkingSpotsRequest;

public interface ParkingService {

  ParkingLot getParkingLotDetail(GetParkingLotDetailRequest request);

  int countOccupiedSpots(GetParkingLotDetailRequest request);

  Page<ParkingSpot> getParkingSpots(int pageNumber, int pageSize, GetParkingSpotsRequest request);
}
