package duke.exception.trivia;

import duke.exception.DukeParseException;

public class EmptyQuestionException extends DukeParseException {
    @Override
    public String getMessage() {
        return "Unable to get an answer for an empty question.";
    }
}
