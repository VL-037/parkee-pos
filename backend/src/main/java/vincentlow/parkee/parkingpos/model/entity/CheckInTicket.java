package vincentlow.parkee.parkingpos.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "check_in_ticket")
@Data
public class CheckInTicket extends BaseEntity {

  @Column(name = "plate_number")
  private String plateNumber;

  @ManyToOne
  @JoinColumn(name = "member_id", referencedColumnName = "id")
  private Member member;

  @ManyToOne
  @JoinColumn(name = "parking_spot_id", referencedColumnName = "id")
  private ParkingSpot parkingSpot;

  @ManyToOne
  @JoinColumn(name = "officer_id", referencedColumnName = "id")
  private Officer officer;

  @Column(name = "is_active")
  private boolean isActive;
}
