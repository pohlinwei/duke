package duke.command.trivia;

import duke.exception.trivia.NoQuestionAskedException;
import duke.trivia.TriviaManager;
import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

public class AnswerCommand extends TriviaCommand {
    private String response;

    public AnswerCommand(String response) {
        this.response = response;
    }

    public String execute(TriviaManager triviaManager, OptionalStorage storage) throws NoQuestionAskedException {
        if (!triviaManager.hasAsked()) {
            throw new NoQuestionAskedException();
        }
        boolean isCorrect = triviaManager.checkAnswer(response);
        return Ui.informResult(isCorrect);
    }
}
