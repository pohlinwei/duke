package duke.command.trivia;

import duke.exception.trivia.MissingAnswerException;
import duke.trivia.TriviaManager;
import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

public class ShowCommand extends TriviaCommand {
    public String execute(TriviaManager triviaManager, OptionalStorage storage) throws MissingAnswerException {
        isMissingAnswer(triviaManager);
        return Ui.showTrivia(triviaManager.toString());
    }
}
