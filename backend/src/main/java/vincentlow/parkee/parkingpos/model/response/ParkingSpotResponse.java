package vincentlow.parkee.parkingpos.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParkingSpotResponse {

  private String id;

  private String spot;

  private boolean isOccupied;
}
