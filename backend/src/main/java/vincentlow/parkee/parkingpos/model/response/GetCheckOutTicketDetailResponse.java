package vincentlow.parkee.parkingpos.model.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCheckOutTicketDetailResponse {

  private String parkingType;

  private String parkingSpot;

  private String memberName;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime memberExpiredDate;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime checkInDate;

  private long durationInSeconds;

  private double price;

  private double discount;

  private double finalPrice;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime createdDate;
}
