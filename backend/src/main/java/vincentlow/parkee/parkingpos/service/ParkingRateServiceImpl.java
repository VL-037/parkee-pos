package vincentlow.parkee.parkingpos.service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vincentlow.parkee.parkingpos.model.entity.ParkingRate;
import vincentlow.parkee.parkingpos.model.entity.Voucher;
import vincentlow.parkee.parkingpos.model.response.ParkingPriceResponse;
import vincentlow.parkee.parkingpos.repository.ParkingRateRepository;
import vincentlow.parkee.parkingpos.util.TimeUtil;

@Service
public class ParkingRateServiceImpl implements ParkingRateService {

  @Autowired
  private ParkingRateRepository parkingRateRepository;

  @Override
  public ParkingPriceResponse calculateParkingPrice(LocalDateTime checkInDate, LocalDateTime checkOutDate,
      Voucher voucher) {

    long parkingDuration = TimeUtil.getParkingDurationInSeconds(checkInDate, checkOutDate);
    int durationInHour = (int) Math.ceil(parkingDuration / 3600.0);

    ParkingRate parkingRate = parkingRateRepository.findTopByMarkForDeleteFalse();
    double ratePerHour = 0;
    if (Objects.nonNull(parkingRate)) {
      ratePerHour = parkingRate.getRatePerHour();
    }

    double price = durationInHour * ratePerHour;
    double discount = getVoucherDiscount(voucher);
    double finalPrice = getFinalPrice(price, discount);

    return ParkingPriceResponse.builder()
        .durationInSeconds(parkingDuration)
        .price(price)
        .discount(discount)
        .finalPrice(finalPrice)
        .build();
  }

  private double getVoucherDiscount(Voucher voucher) {

    return Optional.ofNullable(voucher)
        .map(Voucher::getDiscount)
        .orElse(0.0);
  }

  private static double getFinalPrice(double price, double discount) {

    return (price - discount > 0) ? (price - discount) : 0;
  }
}
