package vincentlow.parkee.parkingpos.service;

import java.time.LocalDateTime;

import vincentlow.parkee.parkingpos.model.entity.Voucher;
import vincentlow.parkee.parkingpos.model.response.ParkingPriceResponse;

public interface ParkingRateService {

  ParkingPriceResponse calculateParkingPrice(LocalDateTime checkInDate, LocalDateTime checkOutDate, Voucher voucher);
}
