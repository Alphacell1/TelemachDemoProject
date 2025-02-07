package hr.tomislav.planinic.telemach.project.repository;

import hr.tomislav.planinic.telemach.project.model.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for ServiceEntity entities.
 */
@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
}
