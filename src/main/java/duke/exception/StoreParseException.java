package duke.exception;

public class StoreParseException extends DukeParseException {
    @Override
    public String getMessage() {
        return String.format("Incorrect format for change storage command. "
            + "Please input 'store <task or trivia> <path>'.");
    }
}
