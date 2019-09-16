package duke.command.trivia;

import duke.exception.trivia.MissingAnswerException;
import duke.exception.trivia.NoTriviaException;
import duke.trivia.TriviaManager;
import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

/**
 * This class returns a command which allows a randomly selected question to be asked.
 */
public class AskCommand extends TriviaCommand {
    /**
     * Asks question from <code>triviaManager</code> randomly. A response to the question is required.
     *
     * @param triviaManager trivia manager that manages all trivia
     * @param storage storage containing all questions and answers
     *
     * @return randomly selected question from <code>triviaManager</code>
     * @throws NoTriviaException if no question and answer was provided previously
     * @throws MissingAnswerException if previously asked question is not answered
     */
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
