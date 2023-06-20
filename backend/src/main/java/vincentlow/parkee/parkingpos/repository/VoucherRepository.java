package vincentlow.parkee.parkingpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vincentlow.parkee.parkingpos.model.entity.Voucher;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, String> {

  @Query("SELECT v FROM Voucher v WHERE v.id = :id AND v.quantity > 0 AND v.expirationDate > CURRENT_TIMESTAMP AND v.markForDelete = false")
  Voucher findValidVoucherByIdAndMarkForDeleteFalse(String id);
}
