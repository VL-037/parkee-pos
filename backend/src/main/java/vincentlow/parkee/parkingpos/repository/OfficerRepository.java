package vincentlow.parkee.parkingpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vincentlow.parkee.parkingpos.model.entity.Officer;

@Repository
public interface OfficerRepository extends JpaRepository<Officer, String> {

  Officer findByIdAndMarkForDeleteFalse(String id);
}
