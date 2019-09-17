package duke.command.trivia;

import duke.exception.trivia.MissingAnswerException;
import duke.trivia.Trivia;
import duke.trivia.TriviaManager;
import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

/**
 * This class returns a command which allows a new <code>question</code> and <code>answer</code>
 * to be added to <code>triviaManager</code>.
 */
public class NewCommand extends TriviaCommand {
    private String question;
    private String answer;

    /**
     * Returns a command that allows a new <code>question</code> and <code>answer</code> to be added.
     *
     * @param question question to be added
     * @param answer answer for <code>question</code>
     */
    public NewCommand(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    /**
     * Creates a new <code>question</code> and <code>answer</code> and adds it to <code>triviaManager</code>.
     *
     * @param triviaManager trivia manager that manages all trivia
     * @param storage storage containing all questions and answers
     * @return string indicating that the new question has been added
     * @throws MissingAnswerException if previously asked question is not answered
     */
    public String execute(TriviaManager triviaManager, OptionalStorage storage) throws MissingAnswerException {
        isMissingAnswer(triviaManager);
        Trivia trivia = new Trivia(question, answer);
        triviaManager.add(trivia);
        storage.add(trivia);
        return Ui.informNew(trivia);
    }
}