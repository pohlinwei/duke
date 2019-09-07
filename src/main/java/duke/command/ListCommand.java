package duke.command;

import duke.task.TaskList;

import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

/**
 * This class allows a new task to be added to a <code>TaskList</code> object.
 */
public class ListCommand implements Command {
    private final static boolean IS_EXIT = false;

    /**
     * Executes this command by listing all tasks in <code>taskList</code>.
     *
     * @param taskList task list which contains all tasks
     * @param storage storage which stores all tasks on the local hard disk, if any
     */
    public String execute(TaskList taskList, OptionalStorage storage) {
        return Ui.showList(taskList.toString());
    }

    public boolean isExit() {
        return IS_EXIT;
    }
}
