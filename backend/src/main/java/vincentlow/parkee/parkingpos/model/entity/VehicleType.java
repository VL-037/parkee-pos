package vincentlow.parkee.parkingpos.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "vehicle_type")
@Data
public class VehicleType extends BaseEntity {

  @Id
  @Column(name = "id", updatable = false, nullable = false)
  private String id;

  @Column(name = "name")
  private String name;
}
