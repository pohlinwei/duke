package duke.exception.trivia;

import duke.exception.DukeException;

public class NoTriviaException extends DukeException {
    @Override
    public String getMessage() {
        return "There is no questions in your trivia bank!";
    }
}
