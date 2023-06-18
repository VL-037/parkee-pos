package vincentlow.parkee.parkingpos.model.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberResponse {

  private String name;

  private String plateNumber;

  private LocalDateTime memberExpiredDate;
}
