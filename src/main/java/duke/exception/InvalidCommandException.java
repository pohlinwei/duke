package duke.exception;

/**
 * This indicates that an error was encountered when trying to parse user's command.
 */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        super();
    }

    @Override
    public String getMessage() {
        return String.format("I'm sorry, but I don't know what that means :-(");
    }
}