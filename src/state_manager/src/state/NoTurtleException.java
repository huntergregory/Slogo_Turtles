package state;

/**
 * Exception for when a turtle is requested, but not found
 * @author David Miron
 */
public class NoTurtleException extends RuntimeException {
    public NoTurtleException() {
        super();
    }
}
