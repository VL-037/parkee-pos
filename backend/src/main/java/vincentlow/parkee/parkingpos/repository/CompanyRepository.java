package vincentlow.parkee.parkingpos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vincentlow.parkee.parkingpos.model.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
}
