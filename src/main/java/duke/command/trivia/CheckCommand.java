package duke.command.trivia;

import duke.exception.trivia.MissingAnswerException;
import duke.exception.trivia.QuestionNotFoundException;
import duke.trivia.TriviaManager;
import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

public class CheckCommand extends TriviaCommand {
    String question;

    public CheckCommand(String question) {
        this.question = question;
    }

    public String execute(TriviaManager triviaManager, OptionalStorage storage) throws QuestionNotFoundException,
        MissingAnswerException {
        isMissingAnswer(triviaManager);
        String answer = triviaManager.findAnswer(question);
        if (answer == null) {
            throw new QuestionNotFoundException();
        } else {
            return Ui.informAnswer(answer);
        }
    }
}
