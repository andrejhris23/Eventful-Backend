package app.eventful.model.exceptions;

public class InvalidEventIdException extends RuntimeException {

    public InvalidEventIdException(Long id) { super("Invalid event id: " + id); }
}
