package duke.exception.trivia;

import duke.exception.DukeException;

public class QuestionNotFoundException extends DukeException {
    @Override
    public String getMessage() {
        return "Unable to find question";
    }
}
