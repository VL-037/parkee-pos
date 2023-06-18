package vincentlow.parkee.parkingpos.model.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "parking_lot_payment_method")
@Data
public class ParkingLotPaymentMethod extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "parking_lot_id", referencedColumnName = "id")
  private ParkingLot parkingLot;

  @ManyToOne
  @JoinColumn(name = "payment_method_id", referencedColumnName = "id")
  private PaymentMethod paymentMethod;
}
