package duke.command.trivia;

import duke.Manager;
import duke.command.Command;
import duke.command.CommandType;
import duke.exception.DukeException;
import duke.exception.trivia.MissingAnswerException;
import duke.trivia.TriviaManager;
import duke.util.storage.OptionalStorage;

/**
 * An <code>abstract</code> class that represents all trivia commands.
 */
abstract class TriviaCommand implements Command {
    private final static CommandType commandType = CommandType.TRIVIA;

    /**
     * Executes a task command, and updates <code>manager</code> and <code>storage</code>, if necessary.
     *
     * @param manager task manager which contains all inputs.
     * @param storage storage which stores all inputs on the local hard disk, if any
     * @return string which updates user about the result of <code>this</code> execution
     * @throws DukeException if execution is unsuccessful
     */
    public String execute(Manager manager, OptionalStorage storage) throws DukeException {
        return execute((TriviaManager) manager, storage);
    }

    /**
     * Executes a task command, and updates <code>manager</code> and <code>storage</code>, if necessary.
     *
     * @param triviaManager task manager which contains all tasks.
     * @param storage storage which stores all tasks on the local hard disk, if any
     * @return string which updates user about the result of <code>this</code> execution
     * @throws DukeException if execution is unsuccessful
     */
    abstract public String execute(TriviaManager triviaManager, OptionalStorage storage) throws DukeException;

    protected void isMissingAnswer(TriviaManager triviaManager) throws MissingAnswerException {
        if (triviaManager.hasAsked()) {
            throw new MissingAnswerException(triviaManager.getAsked());
        }
    }

    /**
     * Gets command type of <code>this</code>.
     *
     * @return command type of <code>this</code> command
     */
    public CommandType getCommandType() {
        return commandType;
    }
}