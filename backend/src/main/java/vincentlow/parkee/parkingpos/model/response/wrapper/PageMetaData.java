package vincentlow.parkee.parkingpos.model.response.wrapper;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageMetaData implements Serializable {

  private static final long serialVersionUID = 6927667702020828247L;

  private long pageNumber;

  private long pageSize;

  private long totalRecords;
}
