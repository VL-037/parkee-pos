package vincentlow.parkee.parkingpos.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "check_out_ticket")
@Data
public class CheckOutTicket extends BaseEntity {

  @Column(name = "price")
  private double price;

  @Column(name = "discount")
  private double discount;

  @Column(name = "final_price")
  private double finalPrice;

  @ManyToOne
  @JoinColumn(name = "officer_id", referencedColumnName = "id")
  private Officer officer;

  @OneToOne
  @JoinColumn(name = "check_in_ticket_id", referencedColumnName = "id")
  private CheckInTicket checkInTicket;

  @ManyToOne
  @JoinColumn(name = "payment_method_id", referencedColumnName = "id")
  private PaymentMethod paymentMethod;
}
