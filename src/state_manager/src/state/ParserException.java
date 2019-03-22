package state;

/**
 * Exception to be thrown if error is encountered while parsing
 * @author Harry Ross
 */
public class ParserException extends Exception {

    /**
     * Creates new ParserException with given message
     * @param message String error message to be included with exception
     */
    public ParserException(String message) {
        super(message);
    }
}
