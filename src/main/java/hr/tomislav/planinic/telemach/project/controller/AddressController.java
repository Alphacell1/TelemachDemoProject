package hr.tomislav.planinic.telemach.project.controller;

import hr.tomislav.planinic.telemach.project.exception.ResourceNotFoundException;
import hr.tomislav.planinic.telemach.project.model.Address;
import hr.tomislav.planinic.telemach.project.repository.AddressRepository;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing Address-related operations.
 */
@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressRepository addressRepository;

    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * Creates a new Address resource.
     *
     * @param address a valid Address object in JSON or XML
     * @return the saved Address
     */
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Address createAddress(@Valid @RequestBody Address address) {
        return addressRepository.save(address);
    }

    /**
     * Retrieves all addresses from the database.
     *
     * @return a list of Address objects
     */
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    /**
     * Retrieves a single Address by its ID.
     *
     * @param id the ID of the Address
     * @return the requested Address
     * @throws ResourceNotFoundException if the Address is not found
     */
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Address getAddressById(@PathVariable Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with ID: " + id));
    }

    /**
     * Updates an existing Address by replacing its field values.
     *
     * @param id       the ID of the Address to update
     * @param updated  the updated Address data
     * @return the newly updated Address
     * @throws ResourceNotFoundException if the Address is not found
     */
    @PutMapping(value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Address updateAddress(@PathVariable Long id, @Valid @RequestBody Address updated) {
        return addressRepository.findById(id)
                .map(existing -> {
                    existing.setStreetNo(updated.getStreetNo());
                    existing.setStreet(updated.getStreet());
                    existing.setCity(updated.getCity());
                    existing.setPost(updated.getPost());
                    existing.setPostNo(updated.getPostNo());
                    return addressRepository.save(existing);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Cannot update. Address not found with ID: " + id));
    }

    /**
     * Deletes an Address by ID.
     *
     * @param id the ID of the Address to delete
     * @throws ResourceNotFoundException if the Address is not found
     */
    @DeleteMapping(value = "/{id}")
    public void deleteAddress(@PathVariable Long id) {
        if (!addressRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. Address not found with ID: " + id);
        }
        addressRepository.deleteById(id);
    }

    /**
     * Searches for Addresses by city name.
     *
     * @param city the city name to search (case-insensitive)
     * @return a list of matching Address objects
     */
    @GetMapping(value = "/search", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Address> searchAddressesByCity(@RequestParam String city) {
        // This is just an example: you'd implement a real query in a custom repo or via Query DSL
        return addressRepository.findAll()
                .stream()
                .filter(addr -> addr.getCity().equalsIgnoreCase(city))
                .toList();
    }
}
