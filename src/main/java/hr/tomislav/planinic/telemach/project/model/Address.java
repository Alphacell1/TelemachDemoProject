package hr.tomislav.planinic.telemach.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an Address entity, which may have many associated services.
 */
@Entity
@Table(name = "addresses")
public class Address extends AuditEntity {
    @Id
    private Long id;

    @NotBlank
    private String streetNo;

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    private String post;

    @NotNull
    private Integer postNo;

    // One Address can have many services
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceEntity> services = new ArrayList<>();

    public Address() {
    }

    public Address(Long id, String streetNo, String street, String city, String post, Integer postNo) {
        this.id = id;
        this.streetNo = streetNo;
        this.street = street;
        this.city = city;
        this.post = post;
        this.postNo = postNo;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) { this.id = id; }

    public String getStreetNo() {
        return streetNo;
    }
    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getPost() {
        return post;
    }
    public void setPost(String post) {
        this.post = post;
    }

    public Integer getPostNo() {
        return postNo;
    }
    public void setPostNo(Integer postNo) {
        this.postNo = postNo;
    }

    public List<ServiceEntity> getServices() {
        return services;
    }
    public void setServices(List<ServiceEntity> services) {
        this.services = services;
    }
}
