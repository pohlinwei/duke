package duke.command.trivia;

import duke.exception.trivia.MissingAnswerException;
import duke.trivia.TriviaManager;
import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

public class RemoveCommand extends TriviaCommand {
    int triviaId;

    public RemoveCommand(int triviaId) {
        this.triviaId = triviaId;
    }

    public String execute(TriviaManager triviaManager, OptionalStorage optionalStorage) throws
        IndexOutOfBoundsException, MissingAnswerException {
        isMissingAnswer(triviaManager);
        triviaManager.remove(triviaId);
        optionalStorage.update(triviaManager.getTriviasAsStream());
        return Ui.informRemoved(triviaManager.getLastEdited(), triviaManager.getSize());
    }
}
