package duke.command.task;

import duke.Manager;
import duke.command.Command;
import duke.command.CommandType;
import duke.exception.DukeException;
import duke.task.TaskManager;
import duke.util.storage.OptionalStorage;

/**
 * An <code>abstract</code> class that represents all task commands.
 */
abstract class TaskCommand extends Command {
    private static final CommandType commandType = CommandType.TASK;

    /**
     * Executes a task command, and updates <code>manager</code> and <code>storage</code>, if necessary.
     *
     * @param manager task manager which contains all inputs.
     * @param storage storage which stores all inputs on the local hard disk, if any
     * @return string which updates user about the result of <code>this</code> execution
     * @throws DukeException if execution is unsuccessful
     */
    public String execute(Manager manager, OptionalStorage storage) throws DukeException {
        return execute((TaskManager) manager, storage);
    }

    /**
     * Executes a task command, and updates <code>manager</code> and <code>storage</code>, if necessary.
     *
     * @param manager task manager which contains all tasks.
     * @param storage storage which stores all tasks on the local hard disk, if any
     * @return string which updates user about the result of <code>this</code> execution
     * @throws DukeException if execution is unsuccessful
     */
    public abstract String execute(TaskManager manager, OptionalStorage storage) throws DukeException;

    /**
     * Gets command type of <code>this</code>.
     *
     * @return command type of <code>this</code> command
     */
    public CommandType getCommandType() {
        return commandType;
    }
}