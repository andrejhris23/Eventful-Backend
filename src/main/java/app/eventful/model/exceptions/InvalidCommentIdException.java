package app.eventful.model.exceptions;

public class InvalidCommentIdException extends RuntimeException{

    public InvalidCommentIdException(Long id) { super("Invalid comment id: " + id); }
}
