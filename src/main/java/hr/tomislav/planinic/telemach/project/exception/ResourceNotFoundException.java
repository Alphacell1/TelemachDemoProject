package hr.tomislav.planinic.telemach.project.exception;

/**
 * Custom exception used to signal that a requested resource was not found.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
