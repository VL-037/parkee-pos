package vincentlow.parkee.parkingpos.model.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParkingLotResponse extends BaseResponse {

  private String companyName;

  private String address;

  private int vehicleCapacity;

  private int occupiedSpots;

  private List<VehicleTypeResponse> vehicleTypes;

  private List<PaymentMethodResponse> paymentMethods;
}
