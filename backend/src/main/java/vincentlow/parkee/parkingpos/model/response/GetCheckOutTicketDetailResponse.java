package vincentlow.parkee.parkingpos.model.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCheckOutTicketDetailResponse {

  private String parkingType;

  private String parkingSpot;

  private String memberName;

  private LocalDateTime memberExpiredDate;

  private LocalDateTime checkInDate;

  private long durationInSeconds;

  private double price;

  private double discount;

  private double finalPrice;

  private LocalDateTime createdDate;
}
