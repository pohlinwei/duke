package duke.exception.task;

import duke.exception.DukeParseException;

/**
 * This indicates that an error was encountered when trying to parse user's event input.
 */
public class EventParseException extends DukeParseException {

    public EventParseException(String string) {
        super(string);
    }

    @Override
    public String getMessage() {
        String specificErrMsg = super.getMessage();
        assert specificErrMsg != "" : "Specific error message for EventParserException should be present";
        return String.format(super.getMessage() + ". Please input event info in the following format:\n\n"
                + "event dance /at 20/1 1800-1830");
    }
}
