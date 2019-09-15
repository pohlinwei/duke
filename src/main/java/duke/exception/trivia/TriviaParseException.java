package duke.exception.trivia;

import duke.exception.DukeParseException;

public class TriviaParseException extends DukeParseException {
    public TriviaParseException(String msg) {
        super(msg);
    }

    @Override
    public String getMessage() {
        return String.format("%s.\nPlease input trivia in the following format:\n\n"
            + "new Where is Singapore? /ans South East Asia", super.getMessage());
    }
}
