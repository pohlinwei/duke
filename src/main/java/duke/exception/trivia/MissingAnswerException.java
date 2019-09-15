package duke.exception.trivia;

import duke.exception.DukeException;

public class MissingAnswerException extends DukeException {
    private String question;

    public MissingAnswerException(String question) {
        this.question = question;
    }

    @Override
    public String getMessage() {
        return String.format("Please answer the previous question in the following format:\nanswer <your answer>"
            + "\n\nPrevious question: %s", question);
    }
}
