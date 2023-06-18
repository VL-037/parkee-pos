package vincentlow.parkee.parkingpos.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "parking_spot")
@Data
public class ParkingSpot extends BaseEntity {

  @Column(name = "area")
  private String area;

  @Column(name = "code")
  private String code;

  @ManyToOne
  @JoinColumn(name = "vehicle_type_id", referencedColumnName = "id")
  private VehicleType vehicleType;

  @Column(name = "is_occupied")
  private boolean isOccupied;

  @ManyToOne
  @JoinColumn(name = "parking_lot_id", referencedColumnName = "id")
  private ParkingLot parkingLot;
}
