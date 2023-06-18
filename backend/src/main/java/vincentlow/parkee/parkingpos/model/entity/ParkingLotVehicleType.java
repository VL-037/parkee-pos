package vincentlow.parkee.parkingpos.model.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "parking_lot_vehicle_type")
@Data
public class ParkingLotVehicleType extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "parking_lot_id", referencedColumnName = "id")
  private ParkingLot parkingLot;

  @ManyToOne
  @JoinColumn(name = "vehicle_type_id", referencedColumnName = "id")
  private VehicleType vehicleType;
}
