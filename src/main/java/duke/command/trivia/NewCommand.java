package duke.command.trivia;

import duke.exception.trivia.MissingAnswerException;
import duke.trivia.Trivia;
import duke.trivia.TriviaManager;
import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

public class NewCommand extends TriviaCommand {
    String question;
    String answer;

    public NewCommand(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String execute(TriviaManager triviaManager, OptionalStorage storage) throws MissingAnswerException {
        isMissingAnswer(triviaManager);
        Trivia trivia = new Trivia(question, answer);
        triviaManager.add(trivia);
        storage.add(trivia);
        return Ui.informNew(trivia);
    }
}
