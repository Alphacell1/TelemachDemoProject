package hr.tomislav.planinic.telemach.project.repository;

import hr.tomislav.planinic.telemach.project.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Address entities.
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
