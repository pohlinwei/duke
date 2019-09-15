package duke.exception.task;

import duke.exception.DukeParseException;

public class MarkDoneParseException extends DukeParseException {
    @Override
    public String getMessage() {
        return "Incorrect format for mark done command. Please input 'done <task number>'.";
    }
}
