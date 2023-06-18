package vincentlow.parkee.parkingpos.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "officer")
@Data
public class Officer extends BaseEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "parking_lot_id", referencedColumnName = "id")
    private ParkingLot parkingLot;
}
