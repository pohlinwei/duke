package duke.command.trivia;

import duke.exception.NoSuchInputException;
import duke.exception.trivia.MissingAnswerException;
import duke.trivia.TriviaManager;
import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

/**
 * This class returns a command which allows a previously added <code>question</code> and <code>answer</code>.
 * to be removed.
 */
public class RemoveCommand extends TriviaCommand {
    private int triviaId;

    /**
     * Returns a command that allows (triviaId)th question and answer to be removed.
     *
     * @param triviaId (triviaId)th in trivia to be removed
     */
    public RemoveCommand(int triviaId) {
        this.triviaId = triviaId;
    }

    /**
     * Removes (triviaId)th trivia.
     *
     * @param triviaManager trivia manager that manages all trivia
     * @param storage storage containing all questions and answers
     * @return string indicating that (triviaId)th trivia has been successfully removed
     * @throws IndexOutOfBoundsException if (triviaId)th trivia doesn't exist
     * @throws MissingAnswerException if previously asked question is not answered
     */
    public String execute(TriviaManager triviaManager, OptionalStorage storage) throws
            NoSuchInputException, MissingAnswerException {
        isMissingAnswer(triviaManager);
        triviaManager.remove(triviaId);
        storage.update(triviaManager.getTriviasAsStream());
        return Ui.informRemoved(triviaManager.getLastEdited(), triviaManager.getSize());
    }
}