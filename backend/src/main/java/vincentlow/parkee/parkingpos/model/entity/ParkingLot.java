package vincentlow.parkee.parkingpos.model.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "parking_lot")
@Data
public class ParkingLot extends BaseEntity {

  @Column(name = "name")
  private String name;

  @Column(name = "vehicle_capacity")
  private int vehicleCapacity;

  @ManyToMany
  @JoinTable(
      name = "parking_lot_vehicle_type",
      joinColumns = @JoinColumn(name = "parking_lot_id"),
      inverseJoinColumns = @JoinColumn(name = "vehicle_type_id"))
  private Set<VehicleType> vehicleTypes;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  private Address address;

  @ManyToOne
  @JoinColumn(name = "company_id", referencedColumnName = "id")
  private Company company;

  @ManyToMany
  @JoinTable(
      name = "parking_lot_payment_method",
      joinColumns = @JoinColumn(name = "parking_lot_id"),
      inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
  private Set<PaymentMethod> paymentMethods;
}
