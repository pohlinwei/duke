package duke.exception.task;

import duke.exception.DukeParseException;

/**
 * Thrown to indicate that event input is incorrectly formatted.
 */
public class EventParseException extends DukeParseException {

    /**
     * Constructs an <code>EventParseException</code> with the specified detail message.
     *
     * @param string the detail message
     */
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
