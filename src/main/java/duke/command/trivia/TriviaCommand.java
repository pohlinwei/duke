package duke.command.trivia;

import duke.Manager;
import duke.command.Command;
import duke.command.CommandType;
import duke.exception.DukeException;
import duke.exception.trivia.MissingAnswerException;
import duke.trivia.TriviaManager;
import duke.util.storage.OptionalStorage;

abstract class TriviaCommand implements Command {
    private final static CommandType commandType = CommandType.TRIVIA;

    public String execute(Manager manager, OptionalStorage storage) throws DukeException {
        return execute((TriviaManager) manager, storage);
    }

    abstract public String execute(TriviaManager triviaManager, OptionalStorage storage) throws DukeException;

    protected void isMissingAnswer(TriviaManager triviaManager) throws MissingAnswerException {
        if (triviaManager.hasAsked()) {
            throw new MissingAnswerException(triviaManager.getAsked());
        }
    }

    public CommandType getCommandType() {
        return commandType;
    }
}