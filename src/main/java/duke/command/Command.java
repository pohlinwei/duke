package duke.command;


import duke.Manager;
import duke.exception.DukeException;
import duke.util.storage.OptionalStorage;

/**
 * This allows commands, as indicated by a user's input, to be executed.
 */
public interface Command {
    /**
     * Executes the intended command.
     *
     * @param manager task list which <code>this</code> task should be added to
     * @param storage storage which stores all tasks on the local hard disk, if any
     * @throws Exception if command cannot be executed
     */
    public String execute(Manager manager, OptionalStorage storage) throws DukeException;

    public CommandType getCommandType();
}
