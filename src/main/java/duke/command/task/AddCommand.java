package duke.command.task;

import duke.task.Task;
import duke.task.TaskManager;
import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

/**
 * This class allows a new task to be added to a <code>TaskList</code> object.
 */
public class AddCommand extends TaskCommand {
    Task task;

    /**
     * Returns a command which allows us to add a task to <code>taskList</code>.
     *
     * @param task task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
        assert this.task != null : "Empty task should not be added";
    }

    /**
     * Executes this command by adding <code>this</code> task to <code>taskList</code>.
     * Informs user that command has been executed.
     *
     * @param taskManager task list which <code>this</code> task should be added to
     * @param storage storage which stores all tasks on the local hard disk, if any
     */
    public String execute(TaskManager taskManager, OptionalStorage storage) {
        taskManager.addTask(task);
        storage.add(task);
        return Ui.informAdded(taskManager.getLastEditedTask(), taskManager.getSize());
    }
}
