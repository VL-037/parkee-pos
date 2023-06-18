package vincentlow.parkee.parkingpos.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "parking_rate")
@Data
public class ParkingRate extends BaseEntity {

  @Column(name = "rate_per_hour")
  private double ratePerHour;
}
