package duke.exception;

/**
 * Thrown when an invalid command is detected.
 */
public class InvalidCommandException extends DukeException {
    @Override
    public String getMessage() {
        return String.format("I'm sorry, but I don't know what that means :-(");
    }
}