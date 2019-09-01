package duke.exception;

import java.text.ParseException;

/**
 * This indicates that an error was encountered when trying to parse user's event input.
 */
public class EventParseException extends ParseException {

    public EventParseException(String string) {
        super(string, 0);
    }

    @Override
    public String getMessage() {
        return String.format(super.getMessage() + ". Please input event info in the following format:\n\n"
                + "event dance /at 20/1 1800-1830");
    }
}
