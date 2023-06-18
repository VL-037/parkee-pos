package vincentlow.parkee.parkingpos.model.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckOutTicketResponse extends BaseResponse {

  private String checkInTicketId;

  private String plateNumber;

  private String parkingType;

  private String parkingSpot;

  private String parkingLotAddress;

  private String officerName;

  private String companyName;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime checkInDate;

  private long duration;

  private String paymentMethodName;

  private double price;

  private double discount;

  private double finalPrice;
}
