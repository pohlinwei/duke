package duke.exception;

/**
 * Thrown to indicate that extra argument(s) in input was found.
 */
public class ExtraArgException extends DukeParseException {
    /**
     * Constructs an <code>ExtraArgException</code> with the specified detail message.
     *
     * @param msg the detail message
     */
    public ExtraArgException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
