package duke.command.trivia;

import duke.exception.trivia.MissingAnswerException;
import duke.exception.trivia.QuestionNotFoundException;
import duke.trivia.TriviaManager;
import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

/**
 * This class allows checking of the answer for a question, if it is present in <code>taskManager</code>.
 */
public class CheckCommand extends TriviaCommand {
    private String question;

    /**
     * Constructs a command that checks the answer of <code>question</code>.
     *
     * @param question question to be looked up
     */
    public CheckCommand(String question) {
        this.question = question;
    }

    /**
     * Checks the answer for <code>question</code>.
     *
     * @param triviaManager trivia manager that manages all trivia
     * @param storage storage containing all questions and answers
     * @return the answer to <code>question</code>
     * @throws QuestionNotFoundException if <code>question</code> has not been previously added
     * @throws MissingAnswerException if previously asked question is not answered
     */
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