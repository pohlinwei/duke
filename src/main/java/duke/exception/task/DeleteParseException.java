package duke.exception.task;

import duke.exception.DukeParseException;

public class DeleteParseException extends DukeParseException {
    @Override
    public String getMessage() {
        return "Incorrect format for delete command. Please input 'delete <task number>'.";
    }
}
