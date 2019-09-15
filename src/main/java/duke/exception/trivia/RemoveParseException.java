package duke.exception.trivia;

import duke.exception.DukeParseException;

public class RemoveParseException extends DukeParseException {
    @Override
    public String getMessage() {
        return "Incorrect format for remove command. Please input 'remove <task number>'.";
    }
}
