package hr.tomislav.planinic.telemach.project.controller;

import hr.tomislav.planinic.telemach.project.exception.ResourceNotFoundException;
import hr.tomislav.planinic.telemach.project.model.Address;
import hr.tomislav.planinic.telemach.project.model.ServiceEntity;
import hr.tomislav.planinic.telemach.project.repository.AddressRepository;
import hr.tomislav.planinic.telemach.project.repository.ServiceRepository;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing Service-related operations.
 */
@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceRepository serviceRepository;
    private final AddressRepository addressRepository;

    public ServiceController(ServiceRepository serviceRepository, AddressRepository addressRepository) {
        this.serviceRepository = serviceRepository;
        this.addressRepository = addressRepository;
    }

    /**
     * Creates a new ServiceEntity associated with an Address (via addressId).
     *
     * @param serviceEntity the service data to create
     * @param addressId     the ID of the Address to which the service is linked
     * @return the saved ServiceEntity
     */
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ServiceEntity createService(@Valid @RequestBody ServiceEntity serviceEntity,
                                       @RequestParam Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with ID: " + addressId));
        serviceEntity.setAddress(address);
        return serviceRepository.save(serviceEntity);
    }

    /**
     * Retrieves all services.
     *
     * @return a list of all ServiceEntity objects
     */
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<ServiceEntity> getAllServices() {
        return serviceRepository.findAll();
    }

    /**
     * Retrieves a ServiceEntity by its ID.
     *
     * @param id the ID of the service
     * @return the requested ServiceEntity
     * @throws ResourceNotFoundException if the Service is not found
     */
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ServiceEntity getServiceById(@PathVariable Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found with ID: " + id));
    }

    /**
     * Updates an existing ServiceEntity.
     *
     * @param id      the ID of the service to update
     * @param updated the new data
     * @return the updated ServiceEntity
     * @throws ResourceNotFoundException if the Service is not found
     */
    @PutMapping(value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ServiceEntity updateService(@PathVariable Long id, @Valid @RequestBody ServiceEntity updated) {
        return serviceRepository.findById(id)
                .map(existing -> {
                    existing.setService(updated.getService());
                    existing.setValue(updated.isValue());
                    existing.setComment(updated.getComment());
                    return serviceRepository.save(existing);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Cannot update. Service not found with ID: " + id));
    }

    /**
     * Deletes a ServiceEntity by ID.
     *
     * @param id the ID of the service
     * @throws ResourceNotFoundException if the Service is not found
     */
    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable Long id) {
        if (!serviceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. Service not found with ID: " + id);
        }
        serviceRepository.deleteById(id);
    }

    /**
     * Retrieves all services for the given address ID.
     *
     * @param addressId the ID of the Address
     * @return a list of ServiceEntity objects linked to that Address
     */
    @GetMapping(value = "/address/{addressId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<ServiceEntity> getServicesByAddress(@PathVariable Long addressId) {
        Address address = addressRepository.findById(addressId).orElse(null);
        return (address != null) ? address.getServices() : List.of();
    }
}
