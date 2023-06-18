package vincentlow.parkee.parkingpos.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckInTicketResponse extends BaseResponse {

  private String plateNumber;

  private String vehicleType;

  private String memberName;

  private String parkingSpot;

  private String parkingLotAddress;

  private String officerName;

  private String companyName;
}
