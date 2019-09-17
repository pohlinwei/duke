package duke.exception.task;

import duke.exception.DukeParseException;

/**
 * Thrown to indicate that delete input is incorrectly formatted.
 */
public class DeleteParseException extends DukeParseException {
    @Override
    public String getMessage() {
        return "Incorrect format for delete command. Please input 'delete <task number>'.";
    }
}
