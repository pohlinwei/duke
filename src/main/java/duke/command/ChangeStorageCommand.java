package duke.command;

import duke.exception.NoStorageChangeException;
import duke.task.TaskList;
import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

public class ChangeStorageCommand implements Command {
    private final static boolean IS_EXIT = false;
    private String path;

    public ChangeStorageCommand(String path) {
        this.path = path;
    }

    public String execute(TaskList taskList, OptionalStorage storage) {
        String resultString = "";

        try {
            storage.update(path);
            storage.updateTaskList(taskList);
            resultString = Ui.informChanged(path);
        } catch (NoStorageChangeException e) {
            resultString = e.getMessage();
        }

        assert !resultString.equals("") : "resultString after storage change should not be empty";

        return resultString;
    }

    public boolean isExit() {
        return IS_EXIT;
    }
}
