package duke.exception;

/**
 * Thrown to indicate that store input is incorrectly formatted.
 */
public class StoreParseException extends DukeParseException {
    @Override
    public String getMessage() {
        return String.format("Incorrect format for change storage command. "
            + "Please input 'store <task or trivia> <path>'.");
    }
}
