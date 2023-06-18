package vincentlow.parkee.parkingpos.model.response.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiSingleResponse<T> extends ApiResponse {

  private T data;
}
