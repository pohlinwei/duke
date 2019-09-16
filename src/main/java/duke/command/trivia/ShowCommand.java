package duke.command.trivia;

import duke.exception.trivia.MissingAnswerException;
import duke.trivia.TriviaManager;
import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

/**
 * This class returns a command that allows all questions and answers in <code>triviaManager</code> to be listed.
 */
public class ShowCommand extends TriviaCommand {
    /**
     * Shows all questions and answers in <code>triviaManager</code>.
     *
     * @param triviaManager trivia manager that manages all trivia
     * @param storage storage containing all questions and answers
     * @return all questions and answers that were previously added
     * @throws MissingAnswerException if previously asked question is not answered
     */
    public String execute(TriviaManager triviaManager, OptionalStorage storage) throws MissingAnswerException {
        isMissingAnswer(triviaManager);
        return Ui.showTrivia(triviaManager.toString());
    }
}
