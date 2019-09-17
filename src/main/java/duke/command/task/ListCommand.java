package duke.command.task;

import duke.task.TaskManager;

import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

/**
 * This class allows a new task to be added to a <code>TaskManager</code> object.
 */
public class ListCommand extends TaskCommand {
    /**
     * Lists all tasks in <code>taskManager</code>.
     *
     * @param taskManager task manager which contains all tasks
     * @param storage storage which stores all tasks on the local hard disk, if any
     * @return all tasks as formatted string
     */
    public String execute(TaskManager taskManager, OptionalStorage storage) {
        return Ui.showList(taskManager.toString());
    }
}