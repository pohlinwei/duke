package duke.exception;

/**
 * Thrown to indicate that an input cannot be found.
 */
public class NoSuchInputException extends DukeException {
    private String msg;

    /**
     * Constructs a <code>NoSuchInputException</code> with the specified detail message.
     *
     * @param msg the detail message
     */
    public NoSuchInputException(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
