package duke.command.trivia;

import duke.exception.trivia.MissingAnswerException;
import duke.exception.trivia.NoTriviaException;
import duke.trivia.TriviaManager;
import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

public class AskCommand extends TriviaCommand {
    public String execute(TriviaManager triviaManager, OptionalStorage storage) throws NoTriviaException,
        MissingAnswerException {
        isMissingAnswer(triviaManager);

        if (triviaManager.isEmpty()) {
            throw new NoTriviaException();
        }
        String question = triviaManager.askRandom();
        return Ui.showQuestion(question);
    }
}
