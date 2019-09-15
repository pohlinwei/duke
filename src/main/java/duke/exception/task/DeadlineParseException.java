package duke.exception.task;

import duke.exception.DukeParseException;

/**
 * This indicates that an error was encountered when trying to parse user's deadline input.
 */
public class DeadlineParseException extends DukeParseException {
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
