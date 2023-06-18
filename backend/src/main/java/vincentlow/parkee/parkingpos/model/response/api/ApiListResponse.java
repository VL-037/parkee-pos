package vincentlow.parkee.parkingpos.model.response.api;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vincentlow.parkee.parkingpos.model.response.wrapper.PageMetaData;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiListResponse<T> {

  private List<T> content;

  private PageMetaData pageMetaData;
}
