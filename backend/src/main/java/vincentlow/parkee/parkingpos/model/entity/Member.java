package vincentlow.parkee.parkingpos.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "member")
@Data
public class Member extends BaseEntity {

  @Column(name = "name")
  private String name;

  @Column(name = "plate_number")
  private String plateNumber;

  @Column(name = "member_expired_date")
  private LocalDateTime memberExpiredDate;
}
