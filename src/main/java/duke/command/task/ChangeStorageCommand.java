package duke.command.task;

import duke.Manager;
import duke.command.Command;
import duke.command.CommandType;
import duke.exception.task.NoStorageChangeException;
import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

/**
 * This class allows the storage file for tasks to be changed.
 */
public class ChangeStorageCommand implements Command {
    private final static CommandType commandType = CommandType.STORE;
    protected String path;

    /**
     * Returns a command which allows file storing tasks to be changed
     *
     * @param path new file location
     */
    public ChangeStorageCommand(String path) {
        this.path = path;
    }

    /**
     * Changes storage file to <code>path</code>. Tasks will be stored there and all tasks from that file will be
     * loaded onto <code>manager</code>. Informs whether the change in storage file has been successful.
     *
     * @param manager task manager which <code>this</code> task should be added to
     * @param storage storage which stores all tasks on the local hard disk, if any
     * @return string indicating whether the execution is successful
     */
    public String execute(Manager manager, OptionalStorage storage) {
        String resultString = "";

        if (path.equals(".")) {
            return (new NoStorageChangeException(path)).getMessage();
        }

        try {
            storage.update(path);
            storage.updateTaskList(manager);
            resultString = Ui.informChanged(path);
        } catch (NoStorageChangeException e) {
            resultString = e.getMessage();
        }

        assert !resultString.equals("") : "resultString after storage change should not be empty";

        return resultString;
    }

    /**
     * Gets command type of this command.
     *
     * @return command type of this command
     */
    public CommandType getCommandType() {
        return commandType;
    }
}