package duke.command.trivia;

import duke.exception.trivia.NoQuestionAskedException;
import duke.trivia.TriviaManager;
import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

/**
 * This class returns a command that allows the previous question to be answered.
 */
public class AnswerCommand extends TriviaCommand {
    private String response;

    /**
     * Returns a command which allows the previous question to be answered. The checking process is not
     * case-sensitive.
     *
     * @param response response to previous question
     */
    public AnswerCommand(String response) {
        this.response = response;
    }

    /**
     * Checks <code>response</code> against the actual <code>answer</code> of the previous <code>trivia</code>.
     *
     * @param triviaManager trivia manager that manages all trivia
     * @param storage storage containing all questions and answers
     * @return string which indicates whether <code>response</code> is correct
     * @throws NoQuestionAskedException if no question was asked previously
     */
    public String execute(TriviaManager triviaManager, OptionalStorage storage) throws NoQuestionAskedException {
        if (!triviaManager.hasAsked()) {
            throw new NoQuestionAskedException();
        }
        boolean isCorrect = triviaManager.checkAnswer(response);
        return Ui.informResult(isCorrect);
    }
}
