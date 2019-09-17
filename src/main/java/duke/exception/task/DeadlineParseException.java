package duke.exception.task;

import duke.exception.DukeParseException;

/**
 * Thrown to indicate that an error was encountered when trying to parse deadline input.
 */
public class DeadlineParseException extends DukeParseException {
    /**
     * Constructs a <code>DeadlineParseException</code> with the specified detail message.
     *
     * @param str the detail message
     */
    public DeadlineParseException(String str) {
        super(str);
    }

    @Override
    public String getMessage() {
        String specificErrMsg = super.getMessage();
        assert specificErrMsg != "" : "Specific error message for DeadlineParseException should be present";
        return String.format(specificErrMsg + ". Please input deadline info in the following format:\n\n"
                + "deadline essay /by 20/1 1800");
    }
}
