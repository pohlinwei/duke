package duke.command.task;

import duke.task.TaskManager;

import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

/**
 * This class allows a new task to be added to a <code>TaskList</code> object.
 */
public class ListCommand extends TaskCommand {
    /**
     * Executes this command by listing all tasks in <code>taskList</code>.
     *
     * @param taskManager task list which contains all tasks
     * @param storage storage which stores all tasks on the local hard disk, if any
     */
    public String execute(TaskManager taskManager, OptionalStorage storage) {
        return Ui.showList(taskManager.toString());
    }
}
