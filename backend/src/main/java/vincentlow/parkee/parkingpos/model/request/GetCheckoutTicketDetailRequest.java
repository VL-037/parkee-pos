package vincentlow.parkee.parkingpos.model.request;

import lombok.Data;

@Data
public class GetCheckoutTicketDetailRequest {

  private String parkingLotId;

  private String parkingSlipId;

  private String vehicleTypeId;

  private String plateNumber;

  private String voucherCode;
}
