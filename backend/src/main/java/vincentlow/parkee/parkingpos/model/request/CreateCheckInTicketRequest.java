package vincentlow.parkee.parkingpos.model.request;

import lombok.Data;

@Data
public class CreateCheckInTicketRequest {

  private String plateNumber;

  private String vehicleTypeId;

  private String parkingLotId;

  private String officerId;
}
