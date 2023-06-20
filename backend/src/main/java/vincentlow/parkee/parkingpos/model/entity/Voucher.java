package vincentlow.parkee.parkingpos.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "voucher")
@Data
public class Voucher extends BaseEntity {

  @Id
  @Column(name = "id", updatable = false, nullable = false)
  private String id;

  @Column(name = "discount")
  private double discount;

  @Column(name = "quantity")
  private int quantity;

  @Column(name = "expiration_date")
  private LocalDateTime expirationDate;
}
