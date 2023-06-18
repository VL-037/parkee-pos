package vincentlow.parkee.parkingpos.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetMemberDetailRequest {

  private String plateNumber;
}
