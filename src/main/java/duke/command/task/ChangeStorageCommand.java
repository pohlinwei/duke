package duke.command.task;

import duke.Manager;
import duke.command.Command;
import duke.command.CommandType;
import duke.exception.task.NoStorageChangeException;
import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

public class ChangeStorageCommand implements Command {
    private final static CommandType commandType = CommandType.STORE;
    protected String path;

    public ChangeStorageCommand(String path) {
        this.path = path;
    }

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

    public CommandType getCommandType() {
        return commandType;
    }
}
