package duke.command.task;

import duke.Manager;
import duke.command.Command;
import duke.command.CommandType;
import duke.exception.DukeException;
import duke.task.TaskManager;
import duke.util.storage.OptionalStorage;

abstract class TaskCommand implements Command {
    private final static CommandType commandType = CommandType.TASK;

    public String execute(Manager manager, OptionalStorage storage) throws DukeException {
        return execute((TaskManager) manager, storage);
    }

    abstract public String execute(TaskManager manager, OptionalStorage storage) throws DukeException;

    public CommandType getCommandType() {
        return commandType;
    }
}
