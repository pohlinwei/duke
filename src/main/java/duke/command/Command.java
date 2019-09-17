package duke.command;


import duke.Manager;
import duke.exception.DukeException;
import duke.util.storage.OptionalStorage;

/**
 * This allows commands, as indicated by the input, to be executed.
 */
public interface Command {
    /**
     * Executes the intended command.
     *
     * @param manager manager containing all inputs
     * @param storage storage which stores all inputs on the local hard disk, if any
     * @throws DukeException if command cannot be executed
     */
    String execute(Manager manager, OptionalStorage storage) throws DukeException;

    /**
     * Gets command type of <code>this</code>.
     *
     * @return command type of <code>this</code> command
     */
    CommandType getCommandType();
}

