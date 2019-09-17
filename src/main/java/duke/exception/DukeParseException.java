package duke.exception;

/**
 * Thrown to indicate that the input cannot be parsed.
 */
public class DukeParseException extends DukeException {
    protected String msg;

    /**
     * Constructs a DukeParseException with the specified detail message.
     * @param msg detailed message
     */
    public DukeParseException(String msg) {
        this.msg = msg;
    }

    /**
     * Constructs a DukeParseException without a specified message.
     */
    public DukeParseException() {

    }

    @Override
    public String getMessage() {
        return msg;
    }
}
