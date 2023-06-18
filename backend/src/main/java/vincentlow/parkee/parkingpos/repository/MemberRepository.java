package vincentlow.parkee.parkingpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vincentlow.parkee.parkingpos.model.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

  Member findByPlateNumberAndMarkForDeleteFalse(String plateNumber);
}
