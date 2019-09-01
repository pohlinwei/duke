package duke.exception;

import java.text.ParseException;

/**
 * This indicates that an error was encountered when trying to parse user's deadline input.
 */
public class DeadlineParseException extends ParseException {
    public DeadlineParseException(String str) {
        super(str, 0);
    }

    @Override
    public String getMessage() {
        return String.format(super.getMessage() + ". Please input deadline info in the following format:\n\n"
                + "deadline essay /by 20/1 1800");
    }
}
