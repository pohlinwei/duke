package duke.exception.trivia;

import duke.exception.DukeException;

public class NoQuestionAskedException extends DukeException {
    @Override
    public String getMessage() {
        return String.format("No question was asked previously. Get a random question by typing 'ask'.");
    }
}
