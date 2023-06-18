package vincentlow.parkee.parkingpos.model.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BaseResponse {

  private String id;

  private LocalDateTime createdDate;

  private String createdBy;

  private LocalDateTime updatedDate;

  private String updatedBy;
}
